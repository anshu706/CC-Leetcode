class Robot {
    int m,n,tot,curr;
    int dirs[];
    Map<Integer,int[]> vals;
    Map<Integer,Integer> dir;
    boolean notstarted;

    public Robot(int width, int height) {
        n=width;
        m=height;
        curr=0;
        notstarted=true;
        dirs=new int[]{0,1,0,-1,0};
        vals=new HashMap<>();
        dir=new HashMap<>();
        tot=0;
        int d=0;
        int tempx=0,tempy=0;
        do{
            vals.put(tot,new int[]{tempy,tempx});
            dir.put(tot,d);
            tot++;
            tempx=tempx+dirs[d];tempy=tempy+dirs[d+1];
            if(tempx<0||tempy<0||tempx>=m||tempy>=n){
                tempx=tempx-dirs[d];tempy=tempy-dirs[d+1];
                d++;
                d=d%4;
                tempx=tempx+dirs[d];tempy=tempy+dirs[d+1];
            }
        }while(tempx!=0||tempy!=0);
    }

    public void step(int num) {
        curr=curr+num;
        curr%=tot;
        notstarted=false;
    }

    public int[] getPos() {
        return vals.get(curr);
    }

    public String getDir() {
        int i=dir.get(curr);
        if(i==0&&(notstarted||curr!=0)) return "East";
        else if(i==1) return "North";
        else if(i==2) return "West";
        else return "South";
    }
} 
