import java.io.*;
import java.util.*;
import java.util.List;

public class main {

    public static void main(String[] args) {
        try {
            String antetInput = "in/";
            String antetOutput = "out/";
            File[] files;
                 files = new File(antetInput).listFiles();
            if(files != null)for(File f : files){
                if(f.isFile()){
                    String name = f.getName();

                    String[] parts = name.split("\\.");
                    String file =antetInput + name;
                    String fileOut = antetOutput + parts[0] + ".out";
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
                    String[] s = br.readLine().split(" ");
                    int n = Integer.parseInt(s[0]);
                    int k = Integer.parseInt(s[1]);
                    Vector<Integer> x = new Vector<Integer>();
                    for (int i = 0; i < n * n; i++) {
                        x.add(0);

                    }
                    int count = 0;
                    String line;
                    while((line = br.readLine()) != null){
                        String[] s1 = line.split(" ");
                        for(int i = 0; i < s1.length; i++){
                            if(!s1[i].equals("")){x.set(count * n + Integer.parseInt(s1[i]) - 1,1);
                                x.set((Integer.parseInt(s1[i]) - 1) * n + count,1);}
                        }
                        count++;
                    }
                    int u = 0, v = 0;
                    for (int i = 0; i < n * n; i++)
                        if (x.get(i) == 0 && i % n != i / n) {
                            u = i / n + 1;
                            v = i % n + 1;
                        }
                    bw.write("p cnf ");
                    int sum = 0;
                    List<Integer> ls = new ArrayList<>();
                    List<List<Integer>> list = new ArrayList<>();
                    for(int i = 1;i <= k * n;i++){
                        ls.add(i);
                    }

                    for(int i = 0;i < n;i++){
                        int cp = ls.get(i);
                        while(cp < k * n){
                            int a = cp + n;
                            while(a <= k * n) {
                                List<Integer> l = new ArrayList<>();
                                sum++;
                                l.add(-cp);
                                l.add(-a);
                                list.add(l);
                                a += n;
                            }
                            cp += n;
                        }
                    }
                    for(int i = 0;i < n;i++){
                        int cp = ls.get(i);
                        int a = ls.get(n);
                        while(a <= 2 * n){
                            if(cp %n != a % n && x.get((cp - 1) * n + (a - n - 1)) == 0) {
                                int x1 = cp, y1, norm = a;
                                while (x1 < k * n) {
                                    y1 = norm;
                                    while(y1 <= k * n) {
                                        List<Integer> l = new ArrayList<>();
                                        sum++;
                                        l.add(-x1);
                                        l.add(-y1);
                                        list.add(l);
                                        y1 += n;
                                    }
                                    x1 += n;
                                    norm += n;
                                }
                            }
                            a++;
                        }
                    }
                    bw.write(k * n + " " + (list.size() + k));
                    bw.newLine();
                    for(int i = 1;i <= k * n;i++){

                        bw.write(i + " ");
                        if(i % n == 0){
                            bw.write("0");
                            bw.newLine();
                        }
                    }
                    if(ls.size() % n != 0){
                        bw.write("0");
                        bw.newLine();
                    }
                    for(int i = 0;i < list.size();i++){
                        bw.write(list.get(i).get(0) + " " + list.get(i).get(1) + " 0");
                        bw.newLine();
                    }
//            bw.newLine();
                    bw.close();
                }
            }


        }catch (NullPointerException e)
        {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}