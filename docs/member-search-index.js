memberSearchIndex = [{
    "p": "commandManager.commands",
    "c": "AddCommand",
    "l": "AddCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBElementCreatorLogic",
    "l": "addCreatorToDB(long, long)",
    "u": "addCreatorToDB(long,long)"
}, {"p": "models.handlers", "c": "CollectionHandler", "l": "addElementToCollection(E)"}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "addElementToCollection(Route)",
    "u": "addElementToCollection(models.Route)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionManager",
    "l": "addElementToDataBase(Route)",
    "u": "addElementToDataBase(models.Route)"
}, {
    "p": "commandManager.commands",
    "c": "AddIfMaxCommand",
    "l": "AddIfMaxCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commands",
    "c": "AddIfMinCommand",
    "l": "AddIfMinCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "addPropertyChangeListener(PropertyChangeListener)",
    "u": "addPropertyChangeListener(java.beans.PropertyChangeListener)"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "ArgumentReceiverHandler",
    "l": "addReceiver(ExternalBaseReceiver)",
    "u": "addReceiver(commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver)"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "NonArgReceiversHandler",
    "l": "addReceiver(ExternalBaseReceiver)",
    "u": "addReceiver(commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver)"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "ReceiverHandler",
    "l": "addReceiver(ExternalBaseReceiver)",
    "u": "addReceiver(commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver)"
}, {"p": "serverLogic", "c": "UdpServerConnection", "l": "address"}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBIntegrationUtility",
    "l": "addRouteToDBAndCollection(Route, long)",
    "u": "addRouteToDBAndCollection(models.Route,long)"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "DBUserManager",
    "l": "addUserToDatabase(CallerBack, RegistrationData)",
    "u": "addUserToDatabase(requestLogic.CallerBack,authorization.authCredentials.RegistrationData)"
}, {"p": "authorization", "c": "UserRoles", "l": "Admin"}, {
    "p": "requestLogic.requestAnnotationProcessors",
    "c": "AnnotationProcessor",
    "l": "AnnotationProcessor(ServerRequest)",
    "u": "%3Cinit%3E(requestLogic.requests.ServerRequest)"
}, {
    "p": "exceptions",
    "c": "AreYouSeriousException",
    "l": "AreYouSeriousException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "requests",
    "c": "ArgumentCommandClientRequest",
    "l": "ArgumentCommandClientRequest(CommandDescription, String[], T)",
    "u": "%3Cinit%3E(commandLogic.CommandDescription,java.lang.String[],T)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "ArgumentCommandClientRequestWorker",
    "l": "ArgumentCommandClientRequestWorker()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "ArgumentReceiverHandler",
    "l": "ArgumentReceiverHandler(Class<T>)",
    "u": "%3Cinit%3E(java.lang.Class)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "ArgumentRequestSender",
    "l": "ArgumentRequestSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic.enums",
    "c": "ReceiverType",
    "l": "ArgumentRoute"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ArgumentRouteCommandReceiver",
    "l": "ArgumentRouteCommandReceiver(ModuleHandler<Route>)",
    "u": "%3Cinit%3E(models.handlers.ModuleHandler)"
}, {
    "p": "authorization.authCredentials",
    "c": "AuthenticationData",
    "l": "AuthenticationData(String, char[])",
    "u": "%3Cinit%3E(java.lang.String,char[])"
}, {
    "p": "requests",
    "c": "AuthorizationRequest",
    "l": "AuthorizationRequest(AuthenticationData)",
    "u": "%3Cinit%3E(authorization.authCredentials.AuthenticationData)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "AuthorizationRequestSender",
    "l": "AuthorizationRequestSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "AuthorizationRequestWorker",
    "l": "AuthorizationRequestWorker()",
    "u": "%3Cinit%3E()"
}, {
    "p": "clientLogic",
    "c": "AuthorizeManager",
    "l": "authorize(CallerBack, AuthenticationData)",
    "u": "authorize(requestLogic.CallerBack,authorization.authCredentials.AuthenticationData)"
}, {
    "p": "clientLogic",
    "c": "AuthorizedCallerBack",
    "l": "AuthorizedCallerBack(AuthorizedUserData, CallerBack)",
    "u": "%3Cinit%3E(authorization.AuthorizedUserData,requestLogic.CallerBack)"
}, {
    "p": "authorization",
    "c": "AuthorizedUserData",
    "l": "AuthorizedUserData(long, String, String, LocalDateTime, String, LocalDateTime)",
    "u": "%3Cinit%3E(long,java.lang.String,java.lang.String,java.time.LocalDateTime,java.lang.String,java.time.LocalDateTime)"
}, {
    "p": "exceptions.authorizationExceptions",
    "c": "AuthorizeException",
    "l": "AuthorizeException(Exception)",
    "u": "%3Cinit%3E(java.lang.Exception)"
}, {
    "p": "exceptions.authorizationExceptions",
    "c": "AuthorizeException",
    "l": "AuthorizeException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "clientLogic",
    "c": "AuthorizeManager",
    "l": "AuthorizeManager()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestAnnotationProcessors.processors",
    "c": "AuthorizeProcessor",
    "l": "AuthorizeProcessor()",
    "u": "%3Cinit%3E()"
}, {
    "p": "responses",
    "c": "AuthorizeResponse",
    "l": "AuthorizeResponse(AuthorizedUserData)",
    "u": "%3Cinit%3E(authorization.AuthorizedUserData)"
}, {
    "p": "requests",
    "c": "BaseRequest",
    "l": "BaseRequest()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "BaseRequestWorker",
    "l": "BaseRequestWorker()",
    "u": "%3Cinit%3E()"
}, {"p": "responses", "c": "BaseResponse", "l": "BaseResponse()", "u": "%3Cinit%3E()"}, {
    "p": "serverLogic",
    "c": "UdpServerConnection",
    "l": "BUFFER_SIZE"
}, {"p": "requestLogic", "c": "StatusRequestBuilder", "l": "build()"}, {
    "p": "models.handlers",
    "c": "ModuleHandler",
    "l": "buildObject()"
}, {
    "p": "models.handlers.nonUserMode",
    "c": "RouteNonCLIHandler",
    "l": "buildObject()"
}, {
    "p": "models.handlers.userMode",
    "c": "CoordinatesCLIHandler",
    "l": "buildObject()"
}, {"p": "models.handlers.userMode", "c": "LocationCLIHandler", "l": "buildObject()"}, {
    "p": "models.handlers.userMode",
    "c": "RouteCLIHandler",
    "l": "buildObject()"
}, {
    "p": "exceptions",
    "c": "BuildObjectException",
    "l": "BuildObjectException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "requestLogic",
    "c": "CallerBack",
    "l": "CallerBack(InetAddress, int)",
    "u": "%3Cinit%3E(java.net.InetAddress,int)"
}, {
    "p": "commandLogic.commandReceiverLogic.callers",
    "c": "ExternalArgumentReceiverCaller",
    "l": "callReceivers(ReceiverManager, CommandDescription, String[])",
    "u": "callReceivers(commandLogic.commandReceiverLogic.ReceiverManager,commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandLogic.commandReceiverLogic.callers",
    "c": "ExternalBaseReceiverCaller",
    "l": "callReceivers(ReceiverManager, CommandDescription, String[])",
    "u": "callReceivers(commandLogic.commandReceiverLogic.ReceiverManager,commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandLogic.commandReceiverLogic.callers",
    "c": "ExternalCaller",
    "l": "callReceivers(ReceiverManager, CommandDescription, String[])",
    "u": "callReceivers(commandLogic.commandReceiverLogic.ReceiverManager,commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "exceptions",
    "c": "CannotProceedException",
    "l": "CannotProceedException(Exception)",
    "u": "%3Cinit%3E(java.lang.Exception)"
}, {"p": "serverLogic", "c": "UdpServerConnection", "l": "channel"}, {
    "p": "models.handlers",
    "c": "Utilities",
    "l": "checkArgumentsOrThrow(int, int)",
    "u": "checkArgumentsOrThrow(int,int)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBElementCreatorLogic",
    "l": "checkNonAccessory(long, long)",
    "u": "checkNonAccessory(long,long)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBIntegrationUtility",
    "l": "clearCollectionInDBAndMemory(long)"
}, {
    "p": "commandManager.commands",
    "c": "ClearCommand",
    "l": "ClearCommand()",
    "u": "%3Cinit%3E()"
}, {"p": "commandManager", "c": "CommandMode", "l": "CLI_UserMode"}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionLoader",
    "l": "close()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionManager",
    "l": "close()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBElementCreatorLogic",
    "l": "close()"
}, {"p": "databaseLogic.databaseUserLogic", "c": "DBUserManager", "l": "close()"}, {
    "p": "serverLogic",
    "c": "ServerConnection",
    "l": "closeConnection()"
}, {"p": "serverLogic", "c": "UdpServerConnection", "l": "closeConnection()"}, {
    "p": "responseLogic",
    "c": "StatusResponse",
    "l": "code()"
}, {
    "p": "commandManager.commandPreProcessing.preProcessors",
    "c": "CommandAuthorizePreProcessor",
    "l": "CommandAuthorizePreProcessor()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requests",
    "c": "CommandClientRequest",
    "l": "CommandClientRequest(CommandDescription, String[])",
    "u": "%3Cinit%3E(commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "CommandClientRequestWorker",
    "l": "CommandClientRequestWorker()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "CommandConfigureRequestWorker",
    "l": "CommandConfigureRequestWorker()",
    "u": "%3Cinit%3E()"
}, {
    "p": "responseLogic.responseSenders",
    "c": "CommandConfigureResponseSender",
    "l": "CommandConfigureResponseSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic",
    "c": "CommandDescription",
    "l": "CommandDescription(String, ExternalCaller)",
    "u": "%3Cinit%3E(java.lang.String,commandLogic.commandReceiverLogic.callers.ExternalCaller)"
}, {
    "p": "requests",
    "c": "CommandDescriptionsRequest",
    "l": "CommandDescriptionsRequest()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestSenders",
    "c": "CommandDescriptionsRequestSender",
    "l": "CommandDescriptionsRequestSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "responses",
    "c": "CommandDescriptionsResponse",
    "l": "CommandDescriptionsResponse(ArrayList<CommandDescription>)",
    "u": "%3Cinit%3E(java.util.ArrayList)"
}, {
    "p": "commandManager",
    "c": "CommandExecutor",
    "l": "CommandExecutor(ArrayList<CommandDescription>, InputStream, CommandMode)",
    "u": "%3Cinit%3E(java.util.ArrayList,java.io.InputStream,commandManager.CommandMode)"
}, {"p": "commandManager", "c": "CommandExporter", "l": "CommandExporter()", "u": "%3Cinit%3E()"}, {
    "p": "exceptions",
    "c": "CommandInterruptedException",
    "l": "CommandInterruptedException(Exception)",
    "u": "%3Cinit%3E(java.lang.Exception)"
}, {
    "p": "commandManager",
    "c": "CommandLoaderUtility",
    "l": "CommandLoaderUtility()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager",
    "c": "CommandManager",
    "l": "CommandManager()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commandPreProcessing",
    "c": "CommandPreProcessorManager",
    "l": "CommandPreProcessorManager()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestSenders",
    "c": "CommandRequestSender",
    "l": "CommandRequestSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "responseLogic.responseSenders",
    "c": "CommandResponseSender",
    "l": "CommandResponseSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "exceptions",
    "c": "CommandsNotLoadedException",
    "l": "CommandsNotLoadedException()",
    "u": "%3Cinit%3E()"
}, {
    "p": "responses",
    "c": "CommandStatusResponse",
    "l": "CommandStatusResponse(String, int)",
    "u": "%3Cinit%3E(java.lang.String,int)"
}, {
    "p": "models.comparators",
    "c": "RouteComparator",
    "l": "compare(Route, Route)",
    "u": "compare(models.Route,models.Route)"
}, {
    "p": "models.comparators",
    "c": "RouteCreationDateComparator",
    "l": "compare(Route, Route)",
    "u": "compare(models.Route,models.Route)"
}, {
    "p": "models.comparators",
    "c": "RouteDistanceComparator",
    "l": "compare(Route, Route)",
    "u": "compare(models.Route,models.Route)"
}, {"p": "models", "c": "Route", "l": "compareTo(Route)", "u": "compareTo(models.Route)"}, {
    "p": "models",
    "c": "Coordinates",
    "l": "Coordinates()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.handlers.userMode",
    "c": "CoordinatesCLIHandler",
    "l": "CoordinatesCLIHandler()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.validators",
    "c": "CoordXValidator",
    "l": "CoordXValidator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.validators",
    "c": "CoordYValidator",
    "l": "CoordYValidator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commands",
    "c": "CountGreaterThanDistanceCommand",
    "l": "CountGreaterThanDistanceCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "serverLogic",
    "c": "DatagramServerConnection",
    "l": "DatagramServerConnection(int)",
    "u": "%3Cinit%3E(int)"
}, {
    "p": "serverLogic",
    "c": "DatagramServerConnectionFactory",
    "l": "DatagramServerConnectionFactory()",
    "u": "%3Cinit%3E()"
}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "DateEditor()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionLoader",
    "l": "DBCollectionLoader(T)",
    "u": "%3Cinit%3E(T)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionManager",
    "l": "DBCollectionManager()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBElementCreatorLogic",
    "l": "DBElementCreatorLogic()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBIntegrationUtility",
    "l": "DBIntegrationUtility()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "DBUserManager",
    "l": "DBUserManager(PasswordEncryption)",
    "u": "%3Cinit%3E(databaseLogic.databaseUserLogic.PasswordEncryption)"
}, {
    "p": "models.validators",
    "c": "DistanceValidator",
    "l": "DistanceValidator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "exceptions",
    "c": "ElementNotAddedException",
    "l": "ElementNotAddedException()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "PasswordEncryption",
    "l": "encrypt(char[])"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "PasswordEncryptionImplSHA512",
    "l": "encrypt(char[])"
}, {
    "p": "authorization",
    "c": "AuthorizedUserData",
    "l": "equals(Object)",
    "u": "equals(java.lang.Object)"
}, {"p": "models", "c": "Coordinates", "l": "equals(Object)", "u": "equals(java.lang.Object)"}, {
    "p": "models",
    "c": "Location",
    "l": "equals(Object)",
    "u": "equals(java.lang.Object)"
}, {"p": "models", "c": "Route", "l": "equals(Object)", "u": "equals(java.lang.Object)"}, {
    "p": "requestLogic",
    "c": "CallerBack",
    "l": "equals(Object)",
    "u": "equals(java.lang.Object)"
}, {
    "p": "responseLogic",
    "c": "StatusResponse",
    "l": "equals(Object)",
    "u": "equals(java.lang.Object)"
}, {
    "p": "responses",
    "c": "ErrorResponse",
    "l": "ErrorResponse(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "commandManager.commands",
    "c": "AddCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "AddIfMaxCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "AddIfMinCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "BaseCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "ClearCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "CountGreaterThanDistanceCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "ExecuteScriptCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "ExitCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "HelpCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "InfoCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "MinByCreationDateCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "PrintFieldDistanceAscendingCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "RemoveByIdCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "SaveCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "ShowCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "UpdateCommand",
    "l": "execute(String[])",
    "u": "execute(java.lang.String[])"
}, {
    "p": "commandManager",
    "c": "CommandManager",
    "l": "executeCommand(CommandClientRequest, CallerBack, ServerConnection)",
    "u": "executeCommand(requests.CommandClientRequest,requestLogic.CallerBack,serverLogic.ServerConnection)"
}, {
    "p": "commandManager.commands",
    "c": "ExecuteScriptCommand",
    "l": "ExecuteScriptCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ExecuteScriptReceiver",
    "l": "ExecuteScriptReceiver()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commands",
    "c": "ExitCommand",
    "l": "ExitCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ExitReceiver",
    "l": "ExitReceiver()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic.callers",
    "c": "ExternalArgumentReceiverCaller",
    "l": "ExternalArgumentReceiverCaller()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic.callers",
    "c": "ExternalBaseReceiverCaller",
    "l": "ExternalBaseReceiverCaller()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic.callers",
    "c": "ExternalCaller",
    "l": "ExternalCaller()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager",
    "c": "CommandManager",
    "l": "fromDescription(CommandDescription)",
    "u": "fromDescription(commandLogic.CommandDescription)"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "PasswordEncryption",
    "l": "generateSalt()"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "PasswordEncryptionImplSHA512",
    "l": "generateSalt()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBIntegrationUtility",
    "l": "getAccessibleCollection(long, Supplier<T>)",
    "u": "getAccessibleCollection(long,java.util.function.Supplier)"
}, {"p": "requestLogic", "c": "CallerBack", "l": "getAddress()"}, {
    "p": "commandManager.commands",
    "c": "AddCommand",
    "l": "getArgs()"
}, {"p": "commandManager.commands", "c": "AddIfMaxCommand", "l": "getArgs()"}, {
    "p": "commandManager.commands",
    "c": "AddIfMinCommand",
    "l": "getArgs()"
}, {"p": "commandManager.commands", "c": "BaseCommand", "l": "getArgs()"}, {
    "p": "commandManager.commands",
    "c": "CountGreaterThanDistanceCommand",
    "l": "getArgs()"
}, {"p": "commandManager.commands", "c": "ExecuteScriptCommand", "l": "getArgs()"}, {
    "p": "commandManager.commands",
    "c": "RemoveByIdCommand",
    "l": "getArgs()"
}, {"p": "commandManager.commands", "c": "RemoveGreaterCommand", "l": "getArgs()"}, {
    "p": "commandManager.commands",
    "c": "UpdateCommand",
    "l": "getArgs()"
}, {
    "p": "commandLogic.commandReceiverLogic.receivers",
    "c": "ExternalArgumentReceiver",
    "l": "getArguemnt()"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ArgumentRouteCommandReceiver",
    "l": "getArguemnt()"
}, {"p": "requests", "c": "ArgumentCommandClientRequest", "l": "getArgument()"}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "getAsText()"
}, {"p": "requests", "c": "AuthorizationRequest", "l": "getAuthenticationData()"}, {
    "p": "responses",
    "c": "AuthorizeResponse",
    "l": "getAuthorizedAs()"
}, {"p": "clientLogic", "c": "Session", "l": "getAuthorizedCallerBack()"}, {
    "p": "clientLogic",
    "c": "AuthorizedCallerBack",
    "l": "getCallerBack()"
}, {"p": "requestLogic", "c": "StatusRequest", "l": "getCallerBack()"}, {
    "p": "requestLogic.authentication",
    "c": "AuthDataHolder",
    "l": "getClientData()"
}, {"p": "requestLogic", "c": "StatusRequest", "l": "getCode()"}, {
    "p": "models.handlers",
    "c": "CollectionHandler",
    "l": "getCollection()"
}, {"p": "models.handlers", "c": "RoutesHandler", "l": "getCollection()"}, {
    "p": "requests",
    "c": "CommandClientRequest",
    "l": "getCommandDescription()"
}, {"p": "commandManager", "c": "CommandDescriptionHolder", "l": "getCommands()"}, {
    "p": "commandManager",
    "c": "CommandManager",
    "l": "getCommands()"
}, {"p": "responses", "c": "CommandDescriptionsResponse", "l": "getCommands()"}, {
    "p": "commandManager",
    "c": "CommandExporter",
    "l": "getCommandsToExport()"
}, {"p": "requestLogic.requests", "c": "ServerRequest", "l": "getConnection()"}, {
    "p": "models",
    "c": "Route",
    "l": "getCoordinates()"
}, {"p": "models", "c": "Route", "l": "getCreationDate()"}, {
    "p": "serverLogic",
    "c": "ServerConnectionHandler",
    "l": "getCurrentConnection()"
}, {"p": "fileLogic.editors", "c": "DateEditor", "l": "getCustomEditor()"}, {
    "p": "exceptions",
    "c": "NotAvailableException",
    "l": "getDeniedClient()"
}, {"p": "commandManager.commands", "c": "AddCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "AddIfMaxCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "AddIfMinCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "BaseCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "ClearCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "CountGreaterThanDistanceCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "ExecuteScriptCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "ExitCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "HelpCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "InfoCommand",
    "l": "getDescr()"
}, {
    "p": "commandManager.commands",
    "c": "MinByCreationDateCommand",
    "l": "getDescr()"
}, {
    "p": "commandManager.commands",
    "c": "PrintFieldDistanceAscendingCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "RemoveByIdCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "SaveCommand", "l": "getDescr()"}, {
    "p": "commandManager.commands",
    "c": "ShowCommand",
    "l": "getDescr()"
}, {"p": "commandManager.commands", "c": "UpdateCommand", "l": "getDescr()"}, {
    "p": "models",
    "c": "Route",
    "l": "getDistance()"
}, {"p": "exceptions", "c": "GotAnErrorResponseException", "l": "getErrorResponse()"}, {
    "p": "models.handlers",
    "c": "CollectionHandler",
    "l": "getFirstOrNew()"
}, {"p": "models.handlers", "c": "RoutesHandler", "l": "getFirstOrNew()"}, {
    "p": "models",
    "c": "Route",
    "l": "getFrom()"
}, {"p": "requestLogic.requests", "c": "ServerRequest", "l": "getFrom()"}, {
    "p": "models",
    "c": "Route",
    "l": "getId()"
}, {"p": "models.handlers", "c": "CollectionHandler", "l": "getInitDate()"}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "getInitDate()"
}, {"p": "requestLogic", "c": "StatusRequest", "l": "getInputStream()"}, {
    "p": "clientLogic",
    "c": "SessionHandler",
    "l": "getInstance()"
}, {"p": "commandManager", "c": "CommandDescriptionHolder", "l": "getInstance()"}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "getInstance()"
}, {"p": "requestLogic.authentication", "c": "AuthDataHolder", "l": "getInstance()"}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "getJavaInitializationString()"
}, {"p": "models.handlers", "c": "CollectionHandler", "l": "getLastElement()"}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "getLastElement()"
}, {"p": "requests", "c": "CommandClientRequest", "l": "getLineArgs()"}, {
    "p": "serverLogic",
    "c": "UdpConnectionBlockDecorator",
    "l": "getLockState()"
}, {"p": "authorization.authCredentials", "c": "AuthenticationData", "l": "getLogin()"}, {
    "p": "models.handlers",
    "c": "CollectionHandler",
    "l": "getMax(Comparator<E>)",
    "u": "getMax(java.util.Comparator)"
}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "getMax(Comparator<Route>)",
    "u": "getMax(java.util.Comparator)"
}, {
    "p": "models.handlers",
    "c": "CollectionHandler",
    "l": "getMin(Comparator<E>)",
    "u": "getMin(java.util.Comparator)"
}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "getMin(Comparator<Route>)",
    "u": "getMin(java.util.Comparator)"
}, {"p": "responses", "c": "ErrorResponse", "l": "getMsg()"}, {
    "p": "authorization.authCredentials",
    "c": "RegistrationData",
    "l": "getName()"
}, {"p": "commandLogic", "c": "CommandDescription", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "AddCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "AddIfMaxCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "AddIfMinCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "BaseCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "ClearCommand",
    "l": "getName()"
}, {
    "p": "commandManager.commands",
    "c": "CountGreaterThanDistanceCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "ExecuteScriptCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "ExitCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "HelpCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "InfoCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "MinByCreationDateCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "PrintFieldDistanceAscendingCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "RemoveByIdCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "SaveCommand", "l": "getName()"}, {
    "p": "commandManager.commands",
    "c": "ShowCommand",
    "l": "getName()"
}, {"p": "commandManager.commands", "c": "UpdateCommand", "l": "getName()"}, {
    "p": "models",
    "c": "Location",
    "l": "getName()"
}, {"p": "models", "c": "Route", "l": "getName()"}, {
    "p": "authorization.authCredentials",
    "c": "AuthenticationData",
    "l": "getPassword()"
}, {"p": "databaseLogic.databaseUserLogic", "c": "PasswordEncryption", "l": "getPepper()"}, {
    "p": "requestLogic",
    "c": "CallerBack",
    "l": "getPort()"
}, {
    "p": "commandLogic",
    "c": "CommandDescription",
    "l": "getReceiver()"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "ArgumentReceiverHandler",
    "l": "getReceivers()"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "NonArgReceiversHandler",
    "l": "getReceivers()"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "ReceiverHandler",
    "l": "getReceivers()"
}, {
    "p": "commandLogic.commandReceiverLogic",
    "c": "ReceiverManager",
    "l": "getReceivers(ReceiverType)",
    "u": "getReceivers(commandLogic.commandReceiverLogic.enums.ReceiverType)"
}, {"p": "requests", "c": "RegistrationRequest", "l": "getRegData()"}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "ResourceStreamLogic",
    "l": "getResourceStream()"
}, {"p": "commandManager.commands", "c": "AddCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "AddIfMaxCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "AddIfMinCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "BaseCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "ClearCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "CountGreaterThanDistanceCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "ExecuteScriptCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "ExitCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "HelpCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "InfoCommand",
    "l": "getResponse()"
}, {
    "p": "commandManager.commands",
    "c": "MinByCreationDateCommand",
    "l": "getResponse()"
}, {
    "p": "commandManager.commands",
    "c": "PrintFieldDistanceAscendingCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "RemoveByIdCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "SaveCommand", "l": "getResponse()"}, {
    "p": "commandManager.commands",
    "c": "ShowCommand",
    "l": "getResponse()"
}, {"p": "commandManager.commands", "c": "UpdateCommand", "l": "getResponse()"}, {
    "p": "responses",
    "c": "CommandStatusResponse",
    "l": "getResponse()"
}, {
    "p": "clientLogic",
    "c": "SessionHandler",
    "l": "getSession(CallerBack)",
    "u": "getSession(requestLogic.CallerBack)"
}, {"p": "clientLogic", "c": "Session", "l": "getSessionCount()"}, {
    "p": "clientLogic",
    "c": "Session",
    "l": "getSessionId()"
}, {"p": "models.handlers", "c": "CollectionHandler", "l": "getSorted()"}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "getSorted()"
}, {"p": "responses", "c": "CommandStatusResponse", "l": "getStatusCode()"}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "getTags()"
}, {"p": "models", "c": "Route", "l": "getTo()"}, {
    "p": "clientLogic",
    "c": "AuthorizedCallerBack",
    "l": "getUserData()"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "DBUserManager",
    "l": "getUserFromDatabase(AuthenticationData)",
    "u": "getUserFromDatabase(authorization.authCredentials.AuthenticationData)"
}, {"p": "requestLogic.requests", "c": "ServerRequest", "l": "getUserRequest()"}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "getValue()"
}, {"p": "models", "c": "Coordinates", "l": "getX()"}, {"p": "models", "c": "Location", "l": "getX()"}, {
    "p": "models",
    "c": "Coordinates",
    "l": "getY()"
}, {"p": "models", "c": "Location", "l": "getY()"}, {"p": "models", "c": "Location", "l": "getZ()"}, {
    "p": "exceptions",
    "c": "GotAnErrorResponseException",
    "l": "GotAnErrorResponseException(ErrorResponse)",
    "u": "%3Cinit%3E(responses.ErrorResponse)"
}, {
    "p": "main",
    "c": "LibUtilities",
    "l": "handleUserInputID(String)",
    "u": "handleUserInputID(java.lang.String)"
}, {"p": "authorization", "c": "AuthorizedUserData", "l": "hashCode()"}, {
    "p": "models",
    "c": "Coordinates",
    "l": "hashCode()"
}, {"p": "models", "c": "Location", "l": "hashCode()"}, {
    "p": "models",
    "c": "Route",
    "l": "hashCode()"
}, {"p": "requestLogic", "c": "CallerBack", "l": "hashCode()"}, {
    "p": "responseLogic",
    "c": "StatusResponse",
    "l": "hashCode()"
}, {
    "p": "models.handlers",
    "c": "Utilities",
    "l": "hasNextLineOrThrow(Scanner)",
    "u": "hasNextLineOrThrow(java.util.Scanner)"
}, {
    "p": "commandManager.commands",
    "c": "HelpCommand",
    "l": "HelpCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commands",
    "c": "InfoCommand",
    "l": "InfoCommand()",
    "u": "%3Cinit%3E()"
}, {"p": "requestLogic", "c": "StatusRequestBuilder", "l": "initialize()"}, {
    "p": "commandManager",
    "c": "CommandDescriptionHolder",
    "l": "initialize(ArrayList<CommandDescription>)",
    "u": "initialize(java.util.ArrayList)"
}, {"p": "commandManager", "c": "CommandLoaderUtility", "l": "initializeCommands()"}, {
    "p": "serverLogic",
    "c": "DatagramServerConnectionFactory",
    "l": "initializeServer(int)"
}, {
    "p": "exceptions",
    "c": "InvalidRequestException",
    "l": "InvalidRequestException()",
    "u": "%3Cinit%3E()"
}, {
    "p": "main",
    "c": "LibUtilities",
    "l": "isNotNumeric(String)",
    "u": "isNotNumeric(java.lang.String)"
}, {"p": "fileLogic.editors", "c": "DateEditor", "l": "isPaintable()"}, {
    "p": "authorization",
    "c": "AuthorizedUserData",
    "l": "lastLogin()"
}, {"p": "main", "c": "LibUtilities", "l": "LibUtilities()", "u": "%3Cinit%3E()"}, {
    "p": "serverLogic",
    "c": "DatagramServerConnection",
    "l": "listenAndGetData()"
}, {
    "p": "fileLogic",
    "c": "Loader",
    "l": "Loader(Class<T>, Class<E>)",
    "u": "%3Cinit%3E(java.lang.Class,java.lang.Class)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionLoader",
    "l": "loadFromDB()"
}, {"p": "databaseLogic.databaseElementLogic", "c": "DBCollectionLoader", "l": "loadFromDB(long)"}, {
    "p": "fileLogic",
    "c": "Loader",
    "l": "loadFromFile(String, BaseReader)",
    "u": "loadFromFile(java.lang.String,fileLogic.BaseReader)"
}, {
    "p": "fileLogic",
    "c": "Loader",
    "l": "loadFromXMLbyEnvKey(String)",
    "u": "loadFromXMLbyEnvKey(java.lang.String)"
}, {"p": "models", "c": "Location", "l": "Location()", "u": "%3Cinit%3E()"}, {
    "p": "models.handlers.userMode",
    "c": "LocationCLIHandler",
    "l": "LocationCLIHandler()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.validators",
    "c": "LocationNameValidator",
    "l": "LocationNameValidator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.validators",
    "c": "LocationXValidator",
    "l": "LocationXValidator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.validators",
    "c": "LocationYZValidator",
    "l": "LocationYZValidator()",
    "u": "%3Cinit%3E()"
}, {"p": "authorization", "c": "AuthorizedUserData", "l": "login()"}, {
    "p": "clientLogic",
    "c": "AuthorizeManager",
    "l": "login(CallerBack)",
    "u": "login(requestLogic.CallerBack)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "LoginRequestSender",
    "l": "LoginRequestSender()",
    "u": "%3Cinit%3E()"
}, {"p": "<Unnamed>", "c": "Main", "l": "Main()", "u": "%3Cinit%3E()"}, {
    "p": "main",
    "c": "Main",
    "l": "Main()",
    "u": "%3Cinit%3E()"
}, {"p": "<Unnamed>", "c": "Main", "l": "main(String[])", "u": "main(java.lang.String[])"}, {
    "p": "main",
    "c": "Main",
    "l": "main(String[])",
    "u": "main(java.lang.String[])"
}, {
    "p": "commandManager.commands",
    "c": "MinByCreationDateCommand",
    "l": "MinByCreationDateCommand()",
    "u": "%3Cinit%3E()"
}, {"p": "authorization", "c": "AuthorizedUserData", "l": "name()"}, {
    "p": "models.validators",
    "c": "NameValidator",
    "l": "NameValidator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic.enums",
    "c": "ReceiverType",
    "l": "NoArgs"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "NonArgReceiversHandler",
    "l": "NonArgReceiversHandler()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.externalRecievers",
    "c": "NonArgumentReceiver",
    "l": "NonArgumentReceiver()",
    "u": "%3Cinit%3E()"
}, {"p": "commandManager", "c": "CommandMode", "l": "NonUserMode"}, {
    "p": "exceptions",
    "c": "NotAvailableException",
    "l": "NotAvailableException(CallerBack)",
    "u": "%3Cinit%3E(requestLogic.CallerBack)"
}, {
    "p": "responses",
    "c": "CommandStatusResponse",
    "l": "ofString(String)",
    "u": "ofString(java.lang.String)"
}, {"p": "serverLogic", "c": "ServerConnection", "l": "openConnection()"}, {
    "p": "serverLogic",
    "c": "UdpServerConnection",
    "l": "openConnection()"
}, {
    "p": "serverLogic",
    "c": "ServerConnectionFactory",
    "l": "openConnection(InetAddress, int)",
    "u": "openConnection(java.net.InetAddress,int)"
}, {
    "p": "serverLogic",
    "c": "UdpServerConnectionFactory",
    "l": "openConnection(InetAddress, int)",
    "u": "openConnection(java.net.InetAddress,int)"
}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "paintValue(Graphics, Rectangle)",
    "u": "paintValue(java.awt.Graphics,java.awt.Rectangle)"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "PasswordEncryptionImplSHA512",
    "l": "PasswordEncryptionImplSHA512()",
    "u": "%3Cinit%3E()"
}, {"p": "<Unnamed>", "c": "Main", "l": "PORT"}, {
    "p": "commandManager.commandPreProcessing",
    "c": "CommandPreProcessorManager",
    "l": "preProceed(BaseCommand, CallerBack, ServerConnection)",
    "u": "preProceed(commandManager.commands.BaseCommand,requestLogic.CallerBack,serverLogic.ServerConnection)"
}, {
    "p": "exceptions",
    "c": "PreProceedingFailedException",
    "l": "PreProceedingFailedException()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commands",
    "c": "PrintFieldDistanceAscendingCommand",
    "l": "PrintFieldDistanceAscendingCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commandPreProcessing.preProcessors",
    "c": "CommandAuthorizePreProcessor",
    "l": "proceed(BaseCommand, CallerBack, ServerConnection)",
    "u": "proceed(commandManager.commands.BaseCommand,requestLogic.CallerBack,serverLogic.ServerConnection)"
}, {
    "p": "commandManager.commandPreProcessing.preProcessors",
    "c": "CommandPreProcessor",
    "l": "proceed(BaseCommand, CallerBack, ServerConnection)",
    "u": "proceed(commandManager.commands.BaseCommand,requestLogic.CallerBack,serverLogic.ServerConnection)"
}, {
    "p": "requestLogic.requestAnnotationProcessors",
    "c": "AnnotationProcessor",
    "l": "proceedAnnotations()"
}, {
    "p": "requestLogic.requestAnnotationProcessors.processors",
    "c": "AuthorizeProcessor",
    "l": "proceedRequest(ServerRequest)",
    "u": "proceedRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestAnnotationProcessors.processors",
    "c": "RequestAnnotationProcessor",
    "l": "proceedRequest(ServerRequest)",
    "u": "proceedRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "exceptions",
    "c": "ProcessionInterruptedException",
    "l": "ProcessionInterruptedException()",
    "u": "%3Cinit%3E()"
}, {
    "p": "fileLogic",
    "c": "BaseReader",
    "l": "readFromFile(String)",
    "u": "readFromFile(java.lang.String)"
}, {
    "p": "fileLogic",
    "c": "XMLReader",
    "l": "readFromFile(String)",
    "u": "readFromFile(java.lang.String)"
}, {"p": "requestLogic", "c": "RequestReader", "l": "readObject()"}, {
    "p": "responseLogic",
    "c": "ResponseReader",
    "l": "readObject()"
}, {
    "p": "commandLogic.commandReceiverLogic.receivers",
    "c": "ExternalBaseReceiver",
    "l": "receiveCommand(CommandDescription, String[])",
    "u": "receiveCommand(commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ArgumentRouteCommandReceiver",
    "l": "receiveCommand(CommandDescription, String[])",
    "u": "receiveCommand(commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ExecuteScriptReceiver",
    "l": "receiveCommand(CommandDescription, String[])",
    "u": "receiveCommand(commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandManager.externalRecievers",
    "c": "ExitReceiver",
    "l": "receiveCommand(CommandDescription, String[])",
    "u": "receiveCommand(commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandManager.externalRecievers",
    "c": "NonArgumentReceiver",
    "l": "receiveCommand(CommandDescription, String[])",
    "u": "receiveCommand(commandLogic.CommandDescription,java.lang.String[])"
}, {
    "p": "commandLogic.commandReceiverLogic.handlers",
    "c": "ReceiverHandler",
    "l": "ReceiverHandler()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandLogic.commandReceiverLogic",
    "c": "ReceiverManager",
    "l": "ReceiverManager()",
    "u": "%3Cinit%3E()"
}, {
    "p": "clientLogic",
    "c": "AuthorizeManager",
    "l": "register(CallerBack, RegistrationData)",
    "u": "register(requestLogic.CallerBack,authorization.authCredentials.RegistrationData)"
}, {"p": "authorization", "c": "AuthorizedUserData", "l": "registerDate()"}, {
    "p": "commandLogic.commandReceiverLogic",
    "c": "ReceiverManager",
    "l": "registerHandler(ReceiverType, ReceiverHandler)",
    "u": "registerHandler(commandLogic.commandReceiverLogic.enums.ReceiverType,commandLogic.commandReceiverLogic.handlers.ReceiverHandler)"
}, {"p": "authorization", "c": "AuthorizedUserData", "l": "registerIP()"}, {
    "p": "commandLogic.commandReceiverLogic",
    "c": "ReceiverManager",
    "l": "registerReceiver(ReceiverType, ExternalBaseReceiver)",
    "u": "registerReceiver(commandLogic.commandReceiverLogic.enums.ReceiverType,commandLogic.commandReceiverLogic.receivers.ExternalBaseReceiver)"
}, {
    "p": "clientLogic",
    "c": "SessionHandler",
    "l": "registerSession(AuthorizedCallerBack)",
    "u": "registerSession(clientLogic.AuthorizedCallerBack)"
}, {
    "p": "authorization.authCredentials",
    "c": "RegistrationData",
    "l": "RegistrationData(String, String, char[])",
    "u": "%3Cinit%3E(java.lang.String,java.lang.String,char[])"
}, {
    "p": "exceptions.authorizationExceptions",
    "c": "RegistrationFailedException",
    "l": "RegistrationFailedException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "requests",
    "c": "RegistrationRequest",
    "l": "RegistrationRequest(RegistrationData)",
    "u": "%3Cinit%3E(authorization.authCredentials.RegistrationData)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "RegistrationRequestSender",
    "l": "RegistrationRequestSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "RegistrationRequestWorker",
    "l": "RegistrationRequestWorker()",
    "u": "%3Cinit%3E()"
}, {
    "p": "commandManager.commands",
    "c": "RemoveByIdCommand",
    "l": "RemoveByIdCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionManager",
    "l": "removeElementFromDatabase(Long)",
    "u": "removeElementFromDatabase(java.lang.Long)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBIntegrationUtility",
    "l": "removeFromCollectionAndDB(long, long)",
    "u": "removeFromCollectionAndDB(long,long)"
}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "RemoveGreaterCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "removePropertyChangeListener(PropertyChangeListener)",
    "u": "removePropertyChangeListener(java.beans.PropertyChangeListener)"
}, {
    "p": "requestLogic",
    "c": "RequestReader",
    "l": "RequestReader(InputStream)",
    "u": "%3Cinit%3E(java.io.InputStream)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "RequestSender",
    "l": "RequestSender()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "RequestWorkerManager",
    "l": "RequestWorkerManager()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseUserLogic",
    "c": "ResourceStreamLogic",
    "l": "ResourceStreamLogic(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {"p": "responseLogic", "c": "StatusResponse", "l": "response()"}, {
    "p": "responseLogic",
    "c": "ResponseReader",
    "l": "ResponseReader(InputStream)",
    "u": "%3Cinit%3E(java.io.InputStream)"
}, {
    "p": "responseLogic.responseSenders",
    "c": "ResponseSender",
    "l": "ResponseSender()",
    "u": "%3Cinit%3E()"
}, {"p": "requests.requestAnnotations", "c": "Authorize", "l": "roles()"}, {
    "p": "models",
    "c": "Route",
    "l": "Route()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.handlers.userMode",
    "c": "RouteCLIHandler",
    "l": "RouteCLIHandler()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.comparators",
    "c": "RouteComparator",
    "l": "RouteComparator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.comparators",
    "c": "RouteCreationDateComparator",
    "l": "RouteCreationDateComparator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.comparators",
    "c": "RouteDistanceComparator",
    "l": "RouteDistanceComparator()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.handlers.nonUserMode",
    "c": "RouteNonCLIHandler",
    "l": "RouteNonCLIHandler(Scanner)",
    "u": "%3Cinit%3E(java.util.Scanner)"
}, {"p": "models.validators", "c": "RouteValidator", "l": "RouteValidator()", "u": "%3Cinit%3E()"}, {
    "p": "fileLogic",
    "c": "Saver",
    "l": "saveCollection(T, String)",
    "u": "saveCollection(T,java.lang.String)"
}, {"p": "commandManager.commands", "c": "SaveCommand", "l": "SaveCommand()", "u": "%3Cinit%3E()"}, {
    "p": "fileLogic",
    "c": "Saver",
    "l": "Saver(Class<E>)",
    "u": "%3Cinit%3E(java.lang.Class)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "CommandRequestSender",
    "l": "sendCommand(CommandDescription, String[], ServerConnection)",
    "u": "sendCommand(commandLogic.CommandDescription,java.lang.String[],serverLogic.ServerConnection)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "ArgumentRequestSender",
    "l": "sendCommand(CommandDescription, String[], T, ServerConnection)",
    "u": "sendCommand(commandLogic.CommandDescription,java.lang.String[],T,serverLogic.ServerConnection)"
}, {"p": "serverLogic", "c": "ServerConnection", "l": "sendData(byte[])"}, {
    "p": "serverLogic",
    "c": "UdpServerConnection",
    "l": "sendData(byte[])"
}, {
    "p": "serverLogic",
    "c": "DatagramServerConnection",
    "l": "sendData(byte[], InetAddress, int)",
    "u": "sendData(byte[],java.net.InetAddress,int)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "AuthorizationRequestSender",
    "l": "sendLoginRequest(AuthenticationData)",
    "u": "sendLoginRequest(authorization.authCredentials.AuthenticationData)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "LoginRequestSender",
    "l": "sendLoginRequest(BaseRequest)",
    "u": "sendLoginRequest(requests.BaseRequest)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "RegistrationRequestSender",
    "l": "sendRegisterRequest(RegistrationData)",
    "u": "sendRegisterRequest(authorization.authCredentials.RegistrationData)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "RequestSender",
    "l": "sendRequest(BaseRequest, ServerConnection)",
    "u": "sendRequest(requests.BaseRequest,serverLogic.ServerConnection)"
}, {
    "p": "requestLogic.requestSenders",
    "c": "CommandDescriptionsRequestSender",
    "l": "sendRequestAndGetCommands()"
}, {
    "p": "responseLogic.responseSenders",
    "c": "ResponseSender",
    "l": "sendResponse(BaseResponse, ServerConnection, CallerBack)",
    "u": "sendResponse(responses.BaseResponse,serverLogic.ServerConnection,requestLogic.CallerBack)"
}, {
    "p": "responseLogic.responseSenders",
    "c": "SuppressIOResponseSender",
    "l": "sendResponse(BaseResponse, ServerConnection, CallerBack)",
    "u": "sendResponse(responses.BaseResponse,serverLogic.ServerConnection,requestLogic.CallerBack)"
}, {
    "p": "responseLogic.responseSenders",
    "c": "CommandConfigureResponseSender",
    "l": "sendResponse(CommandDescriptionsResponse, ServerConnection, CallerBack)",
    "u": "sendResponse(responses.CommandDescriptionsResponse,serverLogic.ServerConnection,requestLogic.CallerBack)"
}, {
    "p": "responseLogic.responseSenders",
    "c": "CommandResponseSender",
    "l": "sendResponse(CommandStatusResponse, ServerConnection, CallerBack)",
    "u": "sendResponse(responses.CommandStatusResponse,serverLogic.ServerConnection,requestLogic.CallerBack)"
}, {
    "p": "serverLogic",
    "c": "ServerConnectionHandler",
    "l": "ServerConnectionHandler()",
    "u": "%3Cinit%3E()"
}, {
    "p": "requestLogic.requests",
    "c": "ServerRequest",
    "l": "ServerRequest(BaseRequest, CallerBack, ServerConnection)",
    "u": "%3Cinit%3E(requests.BaseRequest,requestLogic.CallerBack,serverLogic.ServerConnection)"
}, {
    "p": "clientLogic",
    "c": "Session",
    "l": "Session(AuthorizedCallerBack)",
    "u": "%3Cinit%3E(clientLogic.AuthorizedCallerBack)"
}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "setAsText(String)",
    "u": "setAsText(java.lang.String)"
}, {
    "p": "requestLogic",
    "c": "StatusRequest",
    "l": "setCallerBack(CallerBack)",
    "u": "setCallerBack(requestLogic.CallerBack)"
}, {
    "p": "requestLogic",
    "c": "StatusRequestBuilder",
    "l": "setCallerBack(CallerBack)",
    "u": "setCallerBack(requestLogic.CallerBack)"
}, {"p": "commandManager.commands", "c": "AddCommand", "l": "setCallerID(long)"}, {
    "p": "commandManager.commands",
    "c": "AddIfMaxCommand",
    "l": "setCallerID(long)"
}, {"p": "commandManager.commands", "c": "AddIfMinCommand", "l": "setCallerID(long)"}, {
    "p": "commandManager.commands",
    "c": "AuthorizableCommand",
    "l": "setCallerID(long)"
}, {"p": "commandManager.commands", "c": "ClearCommand", "l": "setCallerID(long)"}, {
    "p": "commandManager.commands",
    "c": "RemoveByIdCommand",
    "l": "setCallerID(long)"
}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "setCallerID(long)"
}, {
    "p": "commandManager.commands",
    "c": "UpdateCommand",
    "l": "setCallerID(long)"
}, {
    "p": "requestLogic.authentication",
    "c": "AuthDataHolder",
    "l": "setClientData(String, char[])",
    "u": "setClientData(java.lang.String,char[])"
}, {"p": "requestLogic", "c": "StatusRequest", "l": "setCode(int)"}, {
    "p": "requestLogic",
    "c": "StatusRequestBuilder",
    "l": "setCode(int)"
}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "setCollection(HashSet<Route>)",
    "u": "setCollection(java.util.HashSet)"
}, {"p": "models.handlers", "c": "CollectionHandler", "l": "setCollection(T)"}, {
    "p": "models",
    "c": "Route",
    "l": "setCoordinates(Coordinates)",
    "u": "setCoordinates(models.Coordinates)"
}, {"p": "models", "c": "Route", "l": "setCreationDate(Date)", "u": "setCreationDate(java.util.Date)"}, {
    "p": "models",
    "c": "Route",
    "l": "setDistance(int)"
}, {"p": "models", "c": "Route", "l": "setFrom(Location)", "u": "setFrom(models.Location)"}, {
    "p": "models",
    "c": "Route",
    "l": "setId(Long)",
    "u": "setId(java.lang.Long)"
}, {
    "p": "requestLogic",
    "c": "StatusRequest",
    "l": "setInputStream(InputStream)",
    "u": "setInputStream(java.io.InputStream)"
}, {"p": "models", "c": "Location", "l": "setName(String)", "u": "setName(java.lang.String)"}, {
    "p": "models",
    "c": "Route",
    "l": "setName(String)",
    "u": "setName(java.lang.String)"
}, {
    "p": "commandManager.commands",
    "c": "AddCommand",
    "l": "setObj(Route)",
    "u": "setObj(models.Route)"
}, {
    "p": "commandManager.commands",
    "c": "AddIfMaxCommand",
    "l": "setObj(Route)",
    "u": "setObj(models.Route)"
}, {
    "p": "commandManager.commands",
    "c": "AddIfMinCommand",
    "l": "setObj(Route)",
    "u": "setObj(models.Route)"
}, {
    "p": "commandManager.commands",
    "c": "RemoveGreaterCommand",
    "l": "setObj(Route)",
    "u": "setObj(models.Route)"
}, {
    "p": "commandManager.commands",
    "c": "UpdateCommand",
    "l": "setObj(Route)",
    "u": "setObj(models.Route)"
}, {"p": "commandManager.commands", "c": "ArgumentConsumer", "l": "setObj(T)"}, {
    "p": "requestLogic",
    "c": "StatusRequestBuilder",
    "l": "setObjectStream(InputStream)",
    "u": "setObjectStream(java.io.InputStream)"
}, {
    "p": "serverLogic",
    "c": "ServerConnectionHandler",
    "l": "setServerConnection(ServerConnection)",
    "u": "setServerConnection(serverLogic.ServerConnection)"
}, {"p": "models", "c": "Route", "l": "setTo(Location)", "u": "setTo(models.Location)"}, {
    "p": "fileLogic",
    "c": "Loader",
    "l": "setupConverter(Class<?>, Class<? extends PropertyEditor>)",
    "u": "setupConverter(java.lang.Class,java.lang.Class)"
}, {
    "p": "fileLogic.editors",
    "c": "DateEditor",
    "l": "setValue(Object)",
    "u": "setValue(java.lang.Object)"
}, {"p": "models", "c": "Coordinates", "l": "setX(double)"}, {
    "p": "models",
    "c": "Location",
    "l": "setX(float)"
}, {"p": "models", "c": "Coordinates", "l": "setY(Float)", "u": "setY(java.lang.Float)"}, {
    "p": "models",
    "c": "Location",
    "l": "setY(Long)",
    "u": "setY(java.lang.Long)"
}, {"p": "models", "c": "Location", "l": "setZ(Long)", "u": "setZ(java.lang.Long)"}, {
    "p": "commandManager.commands",
    "c": "ShowCommand",
    "l": "ShowCommand()",
    "u": "%3Cinit%3E()"
}, {"p": "models.handlers", "c": "CollectionHandler", "l": "sort()"}, {
    "p": "models.handlers",
    "c": "RoutesHandler",
    "l": "sort()"
}, {"p": "commandManager", "c": "CommandExecutor", "l": "startExecuting()"}, {
    "p": "requestLogic",
    "c": "StatusRequest",
    "l": "StatusRequest()",
    "u": "%3Cinit%3E()"
}, {
    "p": "responseLogic",
    "c": "StatusResponse",
    "l": "StatusResponse(String, int)",
    "u": "%3Cinit%3E(java.lang.String,int)"
}, {
    "p": "exceptions",
    "c": "StreamInterruptedException",
    "l": "StreamInterruptedException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {"p": "fileLogic.editors", "c": "DateEditor", "l": "supportsCustomEditor()"}, {
    "p": "responseLogic.responseSenders",
    "c": "SuppressIOResponseSender",
    "l": "SuppressIOResponseSender()",
    "u": "%3Cinit%3E()"
}, {"p": "clientLogic", "c": "Session", "l": "TIMEOUT"}, {
    "p": "responseLogic",
    "c": "StatusResponse",
    "l": "toCommandResponse()"
}, {"p": "authorization", "c": "AuthorizedUserData", "l": "toString()"}, {
    "p": "models",
    "c": "Coordinates",
    "l": "toString()"
}, {"p": "models", "c": "Location", "l": "toString()"}, {
    "p": "models",
    "c": "Route",
    "l": "toString()"
}, {"p": "requestLogic", "c": "CallerBack", "l": "toString()"}, {
    "p": "responseLogic",
    "c": "StatusResponse",
    "l": "toString()"
}, {
    "p": "serverLogic",
    "c": "UdpConnectionBlockDecorator",
    "l": "UdpConnectionBlockDecorator(UdpServerConnection, boolean)",
    "u": "%3Cinit%3E(serverLogic.UdpServerConnection,boolean)"
}, {
    "p": "serverLogic",
    "c": "UdpServerConnection",
    "l": "UdpServerConnection(DatagramChannel, SocketAddress)",
    "u": "%3Cinit%3E(java.nio.channels.DatagramChannel,java.net.SocketAddress)"
}, {
    "p": "serverLogic",
    "c": "UdpServerConnectionFactory",
    "l": "UdpServerConnectionFactory()",
    "u": "%3Cinit%3E()"
}, {
    "p": "exceptions.authorizationExceptions",
    "c": "UnauthorizedException",
    "l": "UnauthorizedException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "exceptions",
    "c": "UnknownCommandException",
    "l": "UnknownCommandException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "exceptions.authorizationExceptions",
    "c": "UnregisteredException",
    "l": "UnregisteredException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "exceptions",
    "c": "UnsupportedRequestException",
    "l": "UnsupportedRequestException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "exceptions",
    "c": "UnsupportedResponseException",
    "l": "UnsupportedResponseException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "commandManager.commands",
    "c": "UpdateCommand",
    "l": "UpdateCommand()",
    "u": "%3Cinit%3E()"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionManager",
    "l": "updateElementInDataBase(Route, Long)",
    "u": "updateElementInDataBase(models.Route,java.lang.Long)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBIntegrationUtility",
    "l": "updateElementInDBAndCollection(Route, long, long)",
    "u": "updateElementInDBAndCollection(models.Route,long,long)"
}, {
    "p": "databaseLogic.databaseElementLogic",
    "c": "DBCollectionManager",
    "l": "updateNullableLocation(Location, ResultSet, int)",
    "u": "updateNullableLocation(models.Location,java.sql.ResultSet,int)"
}, {"p": "authorization", "c": "UserRoles", "l": "User"}, {
    "p": "authorization",
    "c": "AuthorizedUserData",
    "l": "userID()"
}, {"p": "requests.requestAnnotations", "c": "Authorize", "l": "users()"}, {
    "p": "models.handlers",
    "c": "Utilities",
    "l": "Utilities()",
    "u": "%3Cinit%3E()"
}, {
    "p": "models.validators",
    "c": "CoordXValidator",
    "l": "validate(Double)",
    "u": "validate(java.lang.Double)"
}, {
    "p": "models.validators",
    "c": "CoordYValidator",
    "l": "validate(Float)",
    "u": "validate(java.lang.Float)"
}, {
    "p": "models.validators",
    "c": "LocationXValidator",
    "l": "validate(Float)",
    "u": "validate(java.lang.Float)"
}, {
    "p": "models.validators",
    "c": "DistanceValidator",
    "l": "validate(Integer)",
    "u": "validate(java.lang.Integer)"
}, {
    "p": "models.validators",
    "c": "LocationYZValidator",
    "l": "validate(Long)",
    "u": "validate(java.lang.Long)"
}, {
    "p": "models.validators",
    "c": "RouteValidator",
    "l": "validate(Route)",
    "u": "validate(models.Route)"
}, {
    "p": "models.validators",
    "c": "LocationNameValidator",
    "l": "validate(String)",
    "u": "validate(java.lang.String)"
}, {
    "p": "models.validators",
    "c": "NameValidator",
    "l": "validate(String)",
    "u": "validate(java.lang.String)"
}, {"p": "models.validators", "c": "Validator", "l": "validate(T)"}, {
    "p": "authorization",
    "c": "UserRoles",
    "l": "valueOf(String)",
    "u": "valueOf(java.lang.String)"
}, {
    "p": "commandLogic.commandReceiverLogic.enums",
    "c": "ReceiverType",
    "l": "valueOf(String)",
    "u": "valueOf(java.lang.String)"
}, {
    "p": "commandManager",
    "c": "CommandMode",
    "l": "valueOf(String)",
    "u": "valueOf(java.lang.String)"
}, {"p": "authorization", "c": "UserRoles", "l": "values()"}, {
    "p": "commandLogic.commandReceiverLogic.enums",
    "c": "ReceiverType",
    "l": "values()"
}, {"p": "commandManager", "c": "CommandMode", "l": "values()"}, {
    "p": "requestLogic.requestWorkers",
    "c": "ArgumentCommandClientRequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "AuthorizationRequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "BaseRequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "CommandClientRequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "CommandConfigureRequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "RegistrationRequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "RequestWorker",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "requestLogic.requestWorkers",
    "c": "RequestWorkerManager",
    "l": "workWithRequest(ServerRequest)",
    "u": "workWithRequest(requestLogic.requests.ServerRequest)"
}, {
    "p": "fileLogic",
    "c": "BaseWriter",
    "l": "writeToFile(String, LinkedHashMap<String[], String>)",
    "u": "writeToFile(java.lang.String,java.util.LinkedHashMap)"
}, {
    "p": "fileLogic",
    "c": "XMLWriter",
    "l": "writeToFile(String, LinkedHashMap<String[], String>)",
    "u": "writeToFile(java.lang.String,java.util.LinkedHashMap)"
}, {
    "p": "exceptions",
    "c": "WrongAmountOfArgumentsException",
    "l": "WrongAmountOfArgumentsException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {
    "p": "exceptions.authorizationExceptions",
    "c": "WrongPasswordException",
    "l": "WrongPasswordException(String)",
    "u": "%3Cinit%3E(java.lang.String)"
}, {"p": "fileLogic", "c": "XMLReader", "l": "XMLReader()", "u": "%3Cinit%3E()"}, {
    "p": "fileLogic",
    "c": "XMLWriter",
    "l": "XMLWriter()",
    "u": "%3Cinit%3E()"
}];
updateSearchResults();