package evn.petproject.just_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JustShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustShopApplication.class, args);
	}

//	Каждый слой отвечает только за свою задачу:
//
//	controller — принимает запрос, вызывает сервис, возвращает DTO
//
//	service — бизнес-логика
//
//	repository — работа с БД
//
//	dto — только для обмена данными между слоями
//
//	mapper — преобразование Entity ↔ DTO

}
