# Разбор изменений в репозитории

Документ описывает незакоммиченные изменения (working tree) относительно последнего коммита `0ca6663 PageObject`. Затронуто **два файла**:

- `src/test/java/ru/SearchTest.java` — тест регистрации студента;
- `src/test/java/ru/pages/PracticeFormPage.java` — Page Object формы регистрации.

Оба изменения относятся к тесту `studentRegistrationFormTest` и его Page Object. Тест `mentoringPriceTest` и страница `YandexSearchPage` не трогались.

---

## 1. `src/test/java/ru/SearchTest.java`

### 1.1. Новый импорт статического метода `open`

Добавлена строка:

```java
import static com.codeborne.selenide.Selenide.open;
```

Раньше открытие страницы было спрятано внутри метода `PracticeFormPage.openPage()`, поэтому в тесте `open` не использовался напрямую. Теперь тест сам вызывает `open(...)`, и статический импорт нужен, чтобы писать `open(...)` без префикса `Selenide.`.

### 1.2. Константа с URL формы

В класс добавлено поле-константа:

```java
private static final String PRACTICE_FORM_URL = "https://demoqa.com/automation-practice-form";
```

URL «переехал» из Page Object (где он был зашит внутри `openPage()`) в тест и вынесен в именованную константу `static final`. Адрес страницы стал явной частью тестовых данных, а не спрятанной деталью реализации страницы.

### 1.3. Переход от `new PracticeFormPage().openPage()` к `open(URL, PageClass)`

**Было:**

```java
new PracticeFormPage()
        .openPage()
        .setFirstName("Nikita")
        ...
```

**Стало:**

```java
open(PRACTICE_FORM_URL, PracticeFormPage.class)
        .closeAds()
        .setFirstName(firstName)
        ...
```

Ключевые отличия:

- Вместо ручного создания объекта (`new PracticeFormPage()`) и последующего вызова `openPage()` используется штатная фабрика Selenide `open(String url, Class<PageObjectClass>)`. Она открывает URL и сразу возвращает инициализированный экземпляр Page Object — это «каноничный» Selenide-способ работы с Page Object.
- Метод `openPage()` в странице больше не нужен (см. изменения в `PracticeFormPage`), его роль разделилась: открытие страницы взял на себя `open(...)`, а очистку рекламы — отдельный метод `closeAds()`.
- Сразу после открытия в цепочку добавлен вызов `.closeAds()` — он удаляет рекламные баннеры/iframe, мешающие кликам.

### 1.4. Вынесение тестовых данных в локальные переменные

**Было:** значения передавались строковыми литералами прямо в вызовы методов, а в проверке те же значения дублировались ещё раз вручную:

```java
.setFirstName("Nikita")
...
.resultShouldHave(
        "Thanks for submitting the form",
        "Nikita QA",
        "nikita@test.com",
        "Male",
        ...
);
```

**Стало:** все данные объявлены как локальные переменные в начале теста:

```java
String firstName = "Nikita";
String lastName  = "QA";
String email     = "nikita@test.com";
String number    = "1234567890";
String male      = "Male";
String sports    = "Sports";
String subject   = "English";
String address   = "Test address";
String state     = "NCR";
String city      = "Delhi";
```

Эти же переменные используются и при заполнении формы, и при проверке результата. За счёт этого:

- исчезло дублирование «магических строк» — одно значение задаётся в одном месте;
- проверка строится из тех же переменных, что и ввод, поэтому рассинхрон ввода и ожидания стал невозможен.

### 1.5. Составные ожидаемые значения через конкатенацию

В блоке проверки полное имя и пара «штат + город» собираются из переменных:

```java
.verifyCorrectInfo(
    "Thanks for submitting the form",
    firstName + " " + lastName,   // было жёстко: "Nikita QA"
    email,
    male,
    number,
    sports,
    subject,
    address,
    state + " " + city            // было жёстко: "NCR Delhi"
);
```

Раньше «Nikita QA» и «NCR Delhi» были записаны строками. Теперь они формируются конкатенацией реальных входных данных, что отражает, как именно эти значения склеиваются на странице результата.

### 1.6. Переименование метода проверки в вызове

В тесте вызов финальной проверки изменён с `resultShouldHave(...)` на `verifyCorrectInfo(...)` — соответственно переименованию метода в Page Object (см. п. 2.4).

### 1.7. Изменение отступов

Блок объявления переменных и вызов `open(...)` оформлены с отступом в 2 пробела (остальной файл — 4 пробела). Это стилистическая неоднородность: внутри одного метода отступы «плавают». Хороший повод на разборе обсудить единый стиль форматирования (например, прогон через автоформат IDE).

---

## 2. `src/test/java/ru/pages/PracticeFormPage.java`

### 2.1. Объявление полей одним выражением через запятую

**Было:** каждое поле объявлялось отдельной строкой со своим `private final SelenideElement`:

```java
private final SelenideElement firstNameInput = $("#firstName");
private final SelenideElement lastNameInput  = $("#lastName");
...
```

**Стало:** все элементы объявлены одним оператором `private final SelenideElement ... ;` через запятую:

```java
private final SelenideElement firstNameInput = $("#firstName"),
    lastNameInput = $("#lastName"),
    emailInput    = $("#userEmail"),
    ...
    modalContent  = $(".modal-content");
```

Семантически это эквивалентно прежнему коду: те же поля, те же локаторы, тот же модификатор `final`. Изменился только стиль объявления — более компактный, но потенциально менее читаемый/диффируемый. Это дискуссионный момент для разбора: групповое объявление через запятую экономит строки, но усложняет чтение и git-diff при добавлении/удалении отдельного поля. Локаторы (`#firstName`, `#genterWrapper` — с опечаткой в самом id на сайте demoqa, и т.д.) не менялись.

### 2.2. Метод `openPage()` заменён на `closeAds()`

**Было:**

```java
public PracticeFormPage openPage() {
    open("https://demoqa.com/automation-practice-form");

    executeJavaScript(
            "document.querySelectorAll('iframe, footer, #fixedban, #RightSide_Advertisement')" +
                    ".forEach(element => element.remove())"
    );

    return this;
}
```

**Стало:**

```java
public PracticeFormPage closeAds() {
  executeJavaScript(
      "document.querySelectorAll('iframe, footer, #fixedban, #RightSide_Advertisement')" +
          ".forEach(element => element.remove())"
  );

  return this;
}
```

Суть изменения — **разделение ответственности**:

- `open(...)` (открытие страницы) убрано из Page Object — теперь это делает тест через `open(URL, PracticeFormPage.class)`;
- осталась только логика удаления мешающих элементов (реклама, iframe, footer, фиксированный баннер), и метод честно переименован в `closeAds()`, отражая, что он делает;
- метод по-прежнему возвращает `this`, сохраняя fluent-цепочку.

Логика JavaScript-удаления элементов не изменилась — тот же `querySelectorAll(...).forEach(remove)`.

### 2.3. (Без изменений) методы заполнения полей

Методы `setFirstName`, `setLastName`, `setEmail`, `setPhone`, `selectGender`, `selectHobby`, `setSubject`, `setAddress`, `selectState`, `selectCity`, `submit` **не менялись**. Они остались с прежним отступом в 4 пробела (в отличие от изменённых блоков на 2 пробела — см. п. 2.5).

### 2.4. Переименование `resultShouldHave` → `verifyCorrectInfo`

**Было:**

```java
public PracticeFormPage resultShouldHave(String... values) {
    for (String value : values) {
        modalContent.shouldHave(text(value));
    }
    return this;
}
```

**Стало:**

```java
public PracticeFormPage verifyCorrectInfo(String... values) {
    for (String value : values) {
        modalContent.shouldHave(text(value));
    }
    return this;
}
```

Изменилось **только имя метода**, тело осталось прежним: перебор `varargs` и проверка, что модальное окно `.modal-content` содержит каждый ожидаемый текст. Параметр остался `String... values`.

Момент для разбора: оба названия имеют право на жизнь. `resultShouldHave` ближе к Selenide-стилю условий (`shouldHave`) и описывает «что» (результат должен содержать), `verifyCorrectInfo` описывает «зачем» (проверяем корректность данных). Можно обсудить, какой нейминг информативнее для команды.

### 2.5. Изменение отступов в изменённых блоках

Блок объявления полей и метод `closeAds()` оформлены с отступом 2 пробела, тогда как остальные методы класса — 4 пробела. Внутри одного файла соседствуют два стиля отступа. Как и в тесте, стоит привести файл к единому форматированию.

---

## 3. Сводка изменений

| Что | Было | Стало |
|-----|------|-------|
| Открытие страницы | `new PracticeFormPage().openPage()` (open внутри PO) | `open(URL, PracticeFormPage.class)` в тесте |
| URL формы | зашит в `openPage()` | константа `PRACTICE_FORM_URL` в тесте |
| Удаление рекламы | внутри `openPage()` | отдельный метод `closeAds()` |
| Тестовые данные | строковые литералы + дублирование в проверке | локальные переменные, переиспользуемые при вводе и проверке |
| Ожидаемые «имя» и «штат+город» | жёсткие строки `"Nikita QA"`, `"NCR Delhi"` | конкатенация переменных |
| Метод проверки | `resultShouldHave(...)` | `verifyCorrectInfo(...)` |
| Объявление полей PO | по одному на строку | одно выражение через запятую |
| Отступы | 4 пробела | в изменённых блоках 2 пробела (неоднородно) |

## 4. Общая идея изменений

Рефакторинг смещает ответственность по принципу Page Object «как надо»:

- **Тест** управляет данными и точкой входа (URL, открытие страницы, тестовые значения).
- **Page Object** инкапсулирует только взаимодействие с элементами страницы (заполнение, клики, проверки текста), без жёстко зашитого URL и без `open(...)` внутри.

Плюс убрано дублирование тестовых данных и использован штатный Selenide-механизм `open(url, PageObject.class)`.

## 5. Что можно обсудить на разборе

1. **Единый стиль отступов** — в обоих файлах смешаны 2 и 4 пробела; прогнать автоформат.
2. **Групповое объявление полей через запятую** — компактность против читаемости и удобства code review/diff.
3. **Нейминг метода проверки** — `verifyCorrectInfo` vs `resultShouldHave`.
4. **Опечатка в id `#genterWrapper`** — это особенность сайта demoqa (id реально такой), стоит проговорить, чтобы не «исправили» по ошибке.
5. **Отсутствие финального перевода строки** в `SearchTest.java` (`\ No newline at end of file`).
6. **Хардкод данных в тесте** — как следующий шаг можно вынести их в отдельный класс тестовых данных / билдер / `Faker`.
