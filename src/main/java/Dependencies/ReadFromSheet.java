package Dependencies;

import Dependencies.SheetsQuickstart;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFromSheet {
    private static final String APPLICATION_NAME = "Read From Google Spreadsheet";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS,SheetsScopes.DRIVE, SheetsScopes.DRIVE_FILE);
    private static final String CREDENTIALS_FILE_PATH = "C:/Users/ibrah/OneDrive/Documents/Code/NetBeans Projects/MuslimGangDiscordBot/src/main/resources/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static ArrayList<Submission> read(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // TODO : Add you Google Spreadsheet Id here
        // URL : https://github.com/wizArD-1910/Connect-To-Google-Spread-Sheet-Using-Java
        // INFO: Read and Write from Google Spreadsheet using Java
        // BUG: Not Found
        // Simple Comment


        final String spreadsheetId = "1hsrGQxIO5k33SSWKW4MtmWrlhfIMsrFvbZj0TQI2ysQ";

        // TODO : Add Range according to A1 notations
        final String range = "1:100";
		
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
			return null;
        }
        else {
			ArrayList<Submission> submissions = new ArrayList<>();
			
            for (List column : values) {
				if (column.get(0).equals("Timestamp")) {
					continue;
				}
				
				Submission current = new Submission();
				current.setTimestamp((String) column.get(0));
//				submissions.add(new Submission((String) column.get(0), (String) column.get(1), (String) column.get(2), (String) column.get(3), (String) column.get(4), (String) column.get(5), column.get(6).equals("Yes"), column.get(7).equals("Yes")));
            }
			
			return submissions;
        }
    }
}
