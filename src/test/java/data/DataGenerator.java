package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения,
        //  для генерации строки с датой Вы можете использовать класс LocalDate
        //  и его методы для получения и форматирования даты
        LocalDate localDate = LocalDate.now(); //получаем текущую дату
        localDate = localDate.plusDays(shift); //прибавляем shift дней
        String date = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); //перевод даты в формат по шаблону
        return date;
    }

    public static String generateCity(/*String locale*/) {
        // TODO: добавить логику для объявления переменной city и задания её значения,
        //  генерацию можно выполнить с помощью Faker, либо используя массив валидных городов
        //  и класс Random
        String[] city = {"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород",
                "Биробиджан", "Благовещенск", "Брянск", "Великий Новгород", "Владивосток",
                "Владикавказ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Гатчина", "Горно-Алтайск",
                "Грозный", "Донецк", "Екатеринбург", "Запорожье", "Иваново", "Ижевск", "Иркутск",
                "Йошкар-Ола", "Казань", "Калининград", "Калуга",
                "Кемерово", "Киров", "Кострома", "Краснодар", "Красноярск", "Курган",
                "Курск", "Кызыл", "Липецк", "Луганск", "Магадан", "Магас", "Майкоп",
                "Махачкала", "Москва", "Мурманск", "Нальчик", "Нарьян-Мар",
                "Нижний Новгород", "Новосибирск", "Омск", "Орёл", "Оренбург",
                "Пенза", "Пермь", "Петрозаводск", "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону",
                "Рязань", "Салехард", "Самара", "Санкт-Петербург", "Саранск",
                "Саратов", "Севастополь", "Симферополь", "Смоленск", "Ставрополь", "Сыктывкар", "Тамбов",
                "Тверь", "Томск", "Тула", "Тюмень", "Улан-Уде", "Ульяновск", "Уфа", "Хабаровск",
                "Ханты-Мансийск", "Херсон", "Чебоксары", "Челябинск", "Черкесск", "Чита",
                "Элиста", "Южно-Сахалинск", "Якутск", "Ярославль"};

        return city[new Random().nextInt(city.length)];
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения,
        //  для генерации можно использовать Faker
        String name = new Faker(new Locale(locale)).name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения,
        //  для генерации можно использовать Faker

        String phone = new Faker(new Locale(locale)).phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов
            //  generateCity(locale), generateName(locale), generatePhone(locale)

            String city = generateCity();
            String name = generateName(locale);
            String phone = generatePhone(locale);
            UserInfo user = new UserInfo(city, name, phone);
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}