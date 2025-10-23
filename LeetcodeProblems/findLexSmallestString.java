class Solution
{
    public String findLexSmallestString(String s, int a, int b)
    {
        boolean visited[][][]=new boolean[10][10][s.length()];
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{0,0,0});
        int min[]={0,0,0};
        while(!q.isEmpty())
        {
            int temp[]=q.poll();

            if(visited[temp[0]][temp[1]][temp[2]])
                continue;

            min=getMin(temp,min,s,a,b);
            visited[temp[0]][temp[1]][temp[2]]=true;

            for(int i=0;i<=(b&1);i++)
            {
                for(int j=0;j<=1;j++)
                {
                    for(int k=0;k<=1;k++)
                    {
                        int ni=(temp[0]+i)%10;
                        int nj=(temp[1]+j)%10;
                        int nk=(temp[2]+k)%s.length();

                        if(visited[ni][nj][nk])
                            continue;

                        q.offer(new int[]{ni,nj,nk});
                    }
                }
            }
        }

        char[] res=new char[s.length()];
        int n=s.length();
        int sm=(s.length() - (min[2] * b) % s.length() + s.length()) % s.length();
        for (int i=0;i<n;i++)
        {
            int digit=s.charAt(i)-'0';
            
            if (i%2==0)
                digit=(digit+min[0]*a)%10;
            else
                digit=(digit+min[1]*a)%10;
            
            res[i]=(char)(digit+'0');
        }

        String ans=new String(res);

        return ans.substring(sm)+ans.substring(0,sm);
    }

    int[] getMin(int[] states,int min[],String s,int a,int b)
    {
        int sl = (s.length()-(states[2]*b)%s.length())%s.length();
        int ml = (s.length()-(min[2]*b)%s.length())%s.length();

        for(int i=0;i<s.length();i++)
        {
            int jump=states[sl&1];
            int sNum=(s.charAt(sl)-'0'+(jump*a)%10)%10;
            jump=min[ml&1];
            int mNum=(s.charAt(ml)-'0'+(jump*a)%10)%10;

            if(sNum<mNum)
                return states;
            else if(sNum>mNum)
                return min;
            
            sl=(sl+1)%s.length();
            ml=(ml+1)%s.length();
        }

        return min;        
    }
}
