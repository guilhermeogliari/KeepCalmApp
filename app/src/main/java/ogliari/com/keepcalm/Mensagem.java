package ogliari.com.keepcalm;

import java.util.List;

/**
 * Created by guilhermeogliari on 16/05/17.
 */

public class Mensagem {

    private static Mensagem instance = null;

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public static Mensagem getInstance(){
        if(instance == null){
            instance = new Mensagem();
        }
        return instance;
    }
}
