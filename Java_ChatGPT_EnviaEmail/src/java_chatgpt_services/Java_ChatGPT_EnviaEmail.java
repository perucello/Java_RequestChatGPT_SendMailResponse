package java_chatgpt_services;

/**
 * *********************************************
 * EducaCiência FastCode 22/10/2023 Request
 * *********************************************
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import java.util.List;

public class Java_ChatGPT_EnviaEmail {

    public static void chamadaChatGPT_Json() {
        //String token = "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";
        String token = new credenciais.Token_chatGPT().getTokenGerado();

        try {
            String apiKey = token;
            //https://api.openai.com/v1/chat/completions
            String endpoint = "https://api.openai.com/v1/engines/davinci/completions";

            String requestBody = "{\"prompt\": \"Definição de hacker?, GPT-3!\"}";

            // Crie uma conexão HTTP
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

             try ( OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Chamada COM sucesso....");
                StringBuilder response;
                try ( BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                String jsonResponse = response.toString();
                Envia_email.enviaEmail(jsonResponse);
            } else {
                System.out.println("Chamada SEM sucesso....");
                StringBuilder responseFail;
                try ( BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    responseFail = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        responseFail.append(inputLine);
                    }
                }

                String jsonResponseFail = responseFail.toString();
                System.out.println("jsonResponseFail: " + jsonResponseFail);
                Envia_email.enviaEmail(jsonResponseFail);
            }
            connection.disconnect();
        } catch (IOException e) {
            String exception = "Exception: @educacienciafastcode " + e;
            Envia_email.enviaEmail(exception);
        }
    }

    public static void chamadaChatGPT_Model(String pergunta) {

        String token_Generate = new credenciais.Token_chatGPT().getTokenGerado();

        OpenAiService service_ChatGPT = new OpenAiService(token_Generate);

        CompletionRequest request_GPT = CompletionRequest.builder()
                .model("text-davinci-003") // model  -> documentação https://platform.openai.com/docs/models/gpt-3
                .prompt(pergunta) //pergunta ao chatGPT
                .maxTokens(700) // caracteres
                .temperature(0.0) //recebe double , nesse caso deixamos 0.0 para nao variar resposta
                .build();

        List<CompletionChoice> resposta = service_ChatGPT.createCompletion(request_GPT).getChoices();

        String jsonResponse = resposta.toString();
        Envia_email.enviaEmail(jsonResponse);
    }
}
