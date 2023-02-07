package Chat.Server;

import clientService.ClientService;
import commandsService.CommandsService;
import msgService.MsgService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		new ClientService();
		new MsgService();
		new CommandsService();
    }
}
