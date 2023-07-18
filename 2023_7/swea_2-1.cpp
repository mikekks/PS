#include<iostream>

using namespace std;

int ToBit(int n){
    int result = 0;
    int tmp = 0;
    
    while(n> 0){
        tmp = n%10;
        result = result | (1<<tmp);
        n /= 10;
    }
    
    return result;
    
}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    
    int test_case;
    int T;
    int N;
    
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        cin >> N;
        int result = 0;
        int total = N;
        int tmp = ToBit(N);
        result = result | tmp;
        
        while(result != 1023){
            total += N;
            int _Bit = ToBit(total);
            result = _Bit | result;
        }
        

        cout << "#" << test_case << " " << total << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
