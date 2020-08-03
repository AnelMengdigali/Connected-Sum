import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {
    
    public static int connectedSum( int n, List<String> edges ) {
        
        LinkedList<Integer> adj[] = new LinkedList[ n + 1 ];
        Boolean visited[] = new Boolean[ n + 1 ];
        
        LinkedList queue = new LinkedList<>();
        
        int result = 0;
        int answer[] = new int[ n + 1 ];
        
        for( int i = 1; i <= n; i ++ ){
            adj[i] = new LinkedList<>();
        }
        
        Arrays.fill( visited, false );
        
        for( int i = 0; i < edges.size(); i ++ ){
            
            String[] pair = edges.get(i).split(" ");
            if( pair.length >= 2 ){
                
                Integer source = Integer.parseInt( pair[0] );
                Integer dest = Integer.parseInt( pair[1] );
                
                adj[source].addFirst(dest);
                adj[dest].addFirst(source);
                
            }
            
        }
        
        for( int i = 1; i <= n; i ++ ){
            
            if( visited[i] ){
                continue;
            }
            result += 1;
            
            visited[i] = true;
            queue.push(i);
            
            while( queue.size() != 0 ){
                
                int node = (int)queue.remove();
                Iterator iter = adj[ node ].listIterator(0);
                
                while( iter.hasNext() ){
                    
                    int neighb = (int)iter.next();
                    if( !visited[ neighb ] ){
                        
                        visited[ neighb ] = true;
                        queue.add( neighb );
                        result ++;
                        
                    }
                    
                }
                
            }
            answer[i] = result;
            result = 0;
            
        }
        
        for( int i = 0; i < answer.length; i ++ ){
            result += (int)Math.ceil( Math.sqrt( answer[i] ) );
        }
        
        return result;
        
    }
    
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        
        int edgesCount = Integer.parseInt(bufferedReader.readLine().trim());
        
        List<String> edges = IntStream.range(0, edgesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
        .collect(toList());
        
        int result = Result.connectedSum(n, edges);
        
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        
        bufferedReader.close();
        bufferedWriter.close();
    }
}
