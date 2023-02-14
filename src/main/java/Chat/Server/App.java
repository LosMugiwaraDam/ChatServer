package Chat.Server;

import clientService.ClientService;
import util.UtilService;

public class App 
{
    public static void main( String[] args )
    {
		new ClientService();
		new UtilService();
    }
}
