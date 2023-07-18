#include<iostream>

#define Mod 1000000007

using namespace std;

int C[10001][16];

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    
    int test_case;
    int T;
    string s;
    
    cin>>T;

    for(test_case = 1; test_case <= T; ++test_case)
    {
        
        cin >> s;
        long long result = 0;

        int tmp = 1 << (s[0]-65);
        tmp = tmp | 1;
        
        // 초기 케이스 저장
        
        for(int i=0; i<16; i++){
            if((tmp | i) == i)
                C[0][i]++;
        }
        
        
        for(int i=1; i<s.length(); i++){
            int res = s[i]-65;
            
            for(int j=1; j<16; j++){
                int prev = j;
                
                if((prev & (1 << res)) == 0){ // res와 prev가 아예 관련 없는 경우
                    
                    for(int k=1; k<16; k++){
                        if((k & (1 << res)) >= 1){ // res가 포함되면서
                            if((prev & k) > 0)  // && prev 중 하나 or
                                C[i][k] = (C[i][k] + C[i-1][j]) % Mod;
                        }
                    }
                    
                }
                else{   // res에도 있고, prev에도 있는 경우
                    for(int k=1; k<16; k++){
                        if((k & (1 << res)) >= 1)
                            C[i][k] = (C[i][k] + C[i-1][j]) % Mod;
                    }
                }

            }
            
        }
        
        for(int i=1; i<16; i++){
            result = (result + C[s.length()-1][i]) % Mod;

        }
        
        memset(C, '\0', sizeof(C));
        
        cout << "#" << test_case << " " << result << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
