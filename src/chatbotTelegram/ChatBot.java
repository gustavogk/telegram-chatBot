package chatbotTelegram;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class ChatBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		return "Testing Bot";
	}
	
	public void sendMensage(long idTelegram, String mensagem) {
		
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		send.setText(mensagem);
		
		try {
			execute(send);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdateReceived(Update u) {		
		
		//user info
		
		String nome = u.getMessage().getFrom().getFirstName();
		long idTelegram = u.getMessage().getChatId();
		String mensagem = u.getMessage().getText();
		
		System.out.println(nome + " (" + idTelegram + "): " + mensagem);
		
		if(mensagem.contains("hi")) {
			
			mensagem = "Hello";
			
		}else if(mensagem.contains("yes")) {
			
			mensagem = "Sorry, i am just a sample bot";
			
		}else if (!mensagem.contains("hi")&&!mensagem.contains("yes")){
			
			mensagem = "Are you talking to me? Can i help you?";
			
		}
		sendMensage(idTelegram,mensagem);
	}

	@Override
	public String getBotToken() {
		// your bot token
		return "1009680861:AAHv07SSSGNQCJpb60Uy3g1c01xW_4nb6nE";
	}
	
	public static void main(String args[]) {
		
		ApiContextInitializer.init();
		TelegramBotsApi telegramBot = new TelegramBotsApi();
		ChatBot bot = new ChatBot();
		
		try {
			telegramBot.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			System.out.println("Erro no Bot");
			e.printStackTrace();
		}
	}
}