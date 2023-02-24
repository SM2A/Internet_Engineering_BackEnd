package ir.ut.ece.ie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.ut.ece.ie.util.Dispatcher;
import ir.ut.ece.ie.util.ResponseModel;

import java.util.Scanner;

public class OnlineShopApplication {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Dispatcher dispatcher = new Dispatcher();
        Scanner scanner = new Scanner(System.in);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        ResponseModel responseModel = new ResponseModel();
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            try {
                responseModel.setData(dispatcher.dispatch(request));
                responseModel.setSuccess(true);
            } catch (Exception e) {
                responseModel.setData(e.getMessage());
                responseModel.setSuccess(false);
            }
            System.out.println(gson.toJson(responseModel));
        }
    }
}
