package Chat.Server;

import clientService.ClientService;
import util.UtilService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		new ClientService();
		new UtilService();
    }
}
