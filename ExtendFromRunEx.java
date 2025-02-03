@ResponseStatus(HttpStatus.NOT_FOUND)//Status http 404 NOT FOUND in case its not found
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message){
        super(message);//Calls the constructor from Runtime and creates a new instance with the message
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message,cause);//Calls the constructor from Runtime and creates a new instance with the message
    }

}
