#include<iostream>

#define MAX 2001
#define long long ll

using namespace std;

char origin[MAX][MAX];
char pattern[MAX][MAX];
unsigned int hash_origin[MAX][MAX];
unsigned int hash_tmp[MAX][MAX];
unsigned int mulHash[4000000];

int H,W,N,M;

unsigned int getHash(){
    unsigned int target = 0;
    int c = 0;
    
    for(int i=H-1; i>=0; i--){
        for(int j=W-1; j>=0; j--){
            if(pattern[i][j] == 'o')
                target = target + mulHash[c];
            c++;
        }
    }
    
    return target;
}

int check(int a, int b){
    if(origin[a][b] == 'o')
        return 1;
    else
        return 0;
}

int cmpHash(unsigned int target){
    
    int c = 0;
    int res = 0;
    int mul = 5;
    
    for(int i=0; i<N; i++){
        hash_tmp[i][0] = 0;
        c = 0;
        for(int j=W-1; j>=0; j--){
            hash_tmp[i][0] += mulHash[c] * check(i, j);
            c++;
        }
        c--;
        for(int j=1; j<=M-W; j++){
            hash_tmp[i][j] = (hash_tmp[i][j-1] - mulHash[c] * check(i, j-1)) * mul + check(i, j+W-1);
        }
    }
    
    c++;  // 중복되는 것 방지하기 위함
    mul = c;  // 중복되는 것 방지하기 위함
    
    for(int i=0; i <= M-W; i++){
        c = 0;
        hash_origin[0][i] = 0;
        for(int j=H-1; j>=0; j--){
            hash_origin[0][i] += mulHash[c] * hash_tmp[j][i];
            c += mul;
        }
        
        if(target == hash_origin[0][i]){  // 초기 세팅값 확인
            res++;
        }
        
        c -= mul;
        
        for(int j=1; j<=N-H; j++){
            hash_origin[j][i] = (hash_origin[j-1][i] - mulHash[c] * hash_tmp[j-1][i]) * mulHash[mul] + hash_tmp[j+H-1][i];
            
            if(hash_origin[j][i] == target)
                res++;
        }
    }
    
    return res;
}


int main(int argc, char** argv)
{
    int test_case;
    int T;
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    
    //freopen("input.txt", "r", stdin);
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    int mul = 5;
    unsigned int c = 1;
    
    for(int i=0; i< 4000000; i++){
        mulHash[i] = c;
        c = c * mul;
    }
    
    unsigned int target;
    
    for(test_case = 1; test_case <= T; ++test_case)
    {
        
        cin >> H >> W >> N >> M;
        
        for(int i=0; i<H; i++){
            cin >> pattern[i];
        }
        
        for(int i=0; i<N; i++){
            cin >> origin[i];
            
        }
        
        target = getHash();
        int result = cmpHash(target);
        
        cout << "#" << test_case << " " << result << '\n';


    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
