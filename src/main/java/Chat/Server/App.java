package Chat.Server;

import clientService.ClientService;
import commandsService.CommandsService;
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
		new CommandsService();
    }
}
