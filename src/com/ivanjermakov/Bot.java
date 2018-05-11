package com.ivanjermakov;

import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
	
	private List<User> users = new ArrayList<>();
	
	@Override
	public void onUpdateReceived(Update update) {
		
		//message from user
		Message message = new Message(update.getMessage().getText(),
				update.getMessage().getFrom().getUserName(),
				update.getMessage().getDate());
		
		//logs
		System.out.println("---- Message ----");
		System.out.println(message.getUserName());
		System.out.println(message.getText());
		System.out.println(message.getDate());
		
		addUser(message.getUserName());
		
		//response for user
		sendResponse(update, message);
		
	}
	
	@Override
	public String getBotUsername() {
		return "FIT Test Bot";
	}
	
	@Override
	public String getBotToken() {
		return "511665033:AAH7ncz-mgI7nWP2S25npTcvcSrjZD3aD8w";
	}
	
	//all logic there
	private void sendResponse(Update update, Message message) {
		//initial messages
		if (message.getText().equals("/start") && getProgress(message.getUserName()) == User.Progress.INIT) {
			sendWelcomeMessages(update);
			return;
		}
		
		//history messages
		if (message.getText().toLowerCase().equals("да") && getProgress(message.getUserName()) == User.Progress.INIT) {
			sendHistoryMessages(update);
			changeProgress(message.getUserName(), User.Progress.TASK1);
			return;
		}
		
		//task 1
		if (getProgress(message.getUserName()) == User.Progress.TASK1) {
			if (message.getText().toLowerCase().equals("елка") ||
					message.getText().toLowerCase().equals("ёлка")) {
				sendTask2(update);
				changeProgress(message.getUserName(), User.Progress.TASK2);
			} else {
				sendWrongAnswer(update);
			}
			return;
		}
		
		//task 2
		if (getProgress(message.getUserName()) == User.Progress.TASK2) {
			if (message.getText().toLowerCase().equals("rsf37pp")) {
				sendTask3(update);
				changeProgress(message.getUserName(), User.Progress.TASK3);
			} else {
				sendWrongAnswer(update);
			}
			return;
		}

		//task3
		if (getProgress(message.getUserName()) == User.Progress.TASK3) {
			if (message.getText().toLowerCase().equals("пухля")) {
				sendTask4(update);
				changeProgress(message.getUserName(), User.Progress.TASK4);
			} else {
				sendWrongAnswer(update);
			}
			return;
		}

		//task4
		if (getProgress(message.getUserName()) == User.Progress.TASK4) {
			if (message.getText().toLowerCase().equals("зодиак")) {
				endGame(update);
				changeProgress(message.getUserName(), User.Progress.END);
			} else {
				sendWrongAnswer(update);
			}
			return;
		}

		
	}
	
	private void sendDocument(Long chatId, String path) {
		try {
			SendDocument sendDocumentRequest = new SendDocument();
			sendDocumentRequest.setChatId(chatId);
			sendDocumentRequest.setNewDocument(new File(path));
			sendDocumentRequest.setCaption("");
			sendDocument(sendDocumentRequest);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	private void sendWelcomeMessages(Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
			
			sendMessage.setText("Приближается тьма, и скоро всё, что мы знаем, навсегда изменится. Жёлтая стена надвигается, измерение Кошмаров скоро поглотит наш мир…");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Мне нужна твоя помощь! Я не смогу в одиночку победить могущественного демона.");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Готов ли ты сразиться против безумного Одноглазого?..");
			sendMessage(sendMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendHistoryMessages(Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
			
			sendMessage.setText("Вижу, ты решился. Хорошо. Меня зовут Диппер, и я введу тебя в курс дела, чтобы ты знал, что тебе предстоит сделать.");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Прошло всего три года с момента нашей победы над Биллом Шифром через стирание памяти у дяди Стэна. Мы радовались и верили, что нам не придётся столкнуться с ним снова… Но наше счастье оказалось недолгим.");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Не имея телесной оболочки и утратив некоторые свои способности, он всё же смог проникнуть в компьютерный центр университета, в котором ты учишься, и оттуда постепенно распространять свою силу на тысячи серверов! С помощью вируса он хочет отключить всю систему и тогда у людей не будет шансов против него — демон снова будет на свободе и перенесёт в наш мир измерение Кошмаров!");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Я не знаю, почему он выбрал именно БГТУ. Могу лишь предположить, что он надеялся на то, что его не обнаружат, так как никто и не подумает искать источник вируса в университете. Но он прокололся, когда допустил утечку информации — весь этот «бред», на который я наткнулся случайно, заинтересовал меня и я провёл своё расследование. Мои худшие ожидания подтвердились…");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Сейчас я не смогу быть рядом с тобой, потому буду отправлять все инструкции сюда. Однако Билл крайне изобретателен и умён, из-за чего мне придётся шифроваться…");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Удачи, друг! Она тебе очень понадобится…");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Это твоё первое задание. Я отправлю тебе документ, в котором зашифровано слово, являющееся ключом для перехода к следующей инструкции.");
			sendMessage(sendMessage);
			sendDocument(update.getMessage().getChatId(), "images/pic1.jpg");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendWrongAnswer(Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
			
			sendMessage.setText("Мне кажется, что ключ должен выглядеть иначе.");
			sendMessage(sendMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendTask2(Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
			
			sendMessage.setText("Отлично! Ты справился с первым заданием! Так держать!");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Но за это время я установил, что Билл уже внутри сервера Телеграмма, поэтому наш путь становится опаснее и труднее. Мне придётся шифровать инструкции ещё тщательнееу, чтобы мы были всегда на шаг впереди.");
			sendMessage(sendMessage);
			Thread.sleep(5000);
			
			sendMessage.setText("Чтобы найти ключ к следующей инструкции, используй подсказки ниже. Я жду тебя.");
			sendMessage(sendMessage);
			sendDocument(update.getMessage().getChatId(), "images/pic2.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendTask3(Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

			sendMessage.setText("Я хотел встретить тебя в аудитории и передать ключ к Биллу лично, но он следил за мной, и поэтому я оставил там новый шифр, " +
					"внутри которого был код. Это было необходимо, ведь, оставь я последний ключ, этот Одноглазый обязательно бы нас поймал и мы бы проиграли…");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Я рад, что ты справился, но ликовать ещё рано. Вот новая инструкция для третьего ключа.");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Тук-тук-тук… Прислушайся, и найдёшь ответ");
			sendMessage(sendMessage);
			sendDocument(update.getMessage().getChatId(), "sound/What.mp3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendTask4 (Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

			sendMessage.setText("Билл: Ха-ха-ха! Неужели вы и правда надеялись, что я не найду вас здесь? Какие вы легковерные! И как же вы собираетесь " +
					"меня остановить, если неспособны даже спрятаться? Я смеюсь над вашей тупостью! ");
			sendMessage(sendMessage);
			sendDocument(update.getMessage().getChatId(), "images/gif1.gif");
			Thread.sleep(5000);

			sendMessage.setText("Чёрт! Он нашёл нас! Теперь здесь небезопасно…");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Нам нужно встретиться. Последний ключ и с Биллом Шифром будет покончено! Новая инструкция для тебя. Прошу, поторопись!");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Огромная зала, но места здесь мало:\n" +
					"Людей тут полно от утра до заката. \n" +
					"Сюда поспеши, а не то опоздаешь.\n" +
					"Голодным ходить неприятно, ты знаешь!..\n");
			sendMessage(sendMessage);
			sendDocument(update.getMessage().getChatId(), "images/pic3.jpg");
			Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void endGame(Update update) {
		try {
			SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

			sendMessage.setText("Билл: Глупые люди! От меня нет спасения! Я всегда буду существовать, даже если вы будете сражаться!");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Диппер: В этой битве ты проиграл и сейчас я сотру тебя из системы! Твой вирус теперь будет против тебя!");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Билл: Помни, Диппер: реальность — иллюзия, вселенная — голограмма, скупай золото, пока! ");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Диппер: Исчезни, демон!");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendDocument(update.getMessage().getChatId(), "images/pic4.jpg");
			Thread.sleep(5000);

			sendMessage.setText("Мы… Победили? Да, ты сделал это! Теперь Одноглазый ещё какое-то время не потревожит нас, а у меня будет время найти способ навсегда избавиться от него.");
			sendMessage(sendMessage);
			Thread.sleep(5000);

			sendMessage.setText("Спасибо тебе, за твою помощь! Ты справился с Шифром, и за это я хочу отблагодарить тебя. Я вышлю тебе последнюю инструкцию — карту, идя по которой ты можешь встретиться со мной и получить награду за твою работу. Встретимся на назначенном месте!");
			sendMessage(sendMessage);
			sendDocument(update.getMessage().getChatId(), "images/pic5.jpg");
			Thread.sleep(5000);

			sendMessage.setText("Жёлтая стена надвигается… Ничто не укроется…\n" +
					"До скорой встречи.\n");
			sendMessage(sendMessage);
			Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addUser(String userName) {
		if (!containsWithName(userName)) {
			users.add(new User(userName));
		}
	}
	
	private void changeProgress(String userName, User.Progress progress) {
		users
				.stream()
				.filter(o -> o.getUserName().equals(userName))
				.forEach(e -> e.setProgress(progress));
	}
	
	private boolean containsWithName(String userName) {
		return users
				.stream()
				.anyMatch(o -> o.getUserName().equals(userName));
	}
	
	private User.Progress getProgress(String userName) {
		try {
			return users
					.stream()
					.filter(o -> o.getUserName().equals(userName))
					.findFirst()
					.get()
					.getProgress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
