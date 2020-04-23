
package control;

import java.io.File;

/**
 * @author Walter Quichimbo
 */
public class UtilPath {
    
    public static String getPathDefinida(String path){
        if(path != null){
            StringBuilder rutaDefinida = new StringBuilder();
            for(int i = 0; i < path.length(); i++){
                char characterValue = path.charAt(i);
                int asciiValue = (int) characterValue;
                int newAsciiValue = 0;
                StringBuilder newCharacterValue = new StringBuilder();
                if(asciiValue == 47 || asciiValue == 92){
                    for(int n = i + 1; asciiValue == 47 || asciiValue == 92; n++){
                        newAsciiValue = (int) path.charAt(n);
                        if(newAsciiValue == 47 || newAsciiValue == 92){
                            i = n-1;
                            asciiValue = -1;
                        } else{
                            newCharacterValue.append(path.charAt(n));
                        }
                    }                
                    String value = newCharacterValue.toString();               
                    if(value.equals("build")){
                        i = path.length();
                    } else{
                        rutaDefinida.append(File.separator).append(value);
                    }
                }
            }
            return rutaDefinida.toString();
        } else{
            return null;
        }
    }
    
    
}
