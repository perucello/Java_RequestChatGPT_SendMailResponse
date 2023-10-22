package run;

/**
 * *********************************************
 * EducaCiência FastCode 22/10/2023 Executável
 * *********************************************
 */
public class Run {

    public static void main(String[] args) {

        java_chatgpt_services.
                Java_ChatGPT_EnviaEmail.chamadaChatGPT_Json();

        String pergunta = "Defina a linguagem Java";
        java_chatgpt_services.
                Java_ChatGPT_EnviaEmail.chamadaChatGPT_Model(pergunta);
    }

}
