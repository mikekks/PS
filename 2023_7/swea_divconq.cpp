#include<iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;

typedef unsigned long long ll;

int N;
ll A,B,K;

ll power(ll x, ll k){
    ll res = 1;
    while(k != 0){
        if(k%2 == 1) res = (res * x)%(A+B);
        x = (x*x)%(A+B);
        k = k >> 1;
    }
    return res;
}

ll divConq(int k){
    ll res = (power(2,k) * A) % (A+B);
    return res < (A+B-res) ? res : A+B-res;
}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int test_case;
    int T;

    //freopen("input.txt", "r", stdin);
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    
    for(test_case = 1; test_case <= T; ++test_case)
    {
        
        
        
        cin >> A >> B >> K;
        
        ll total = A+B;
        ll result = 0;
        
        if(K == 1){
            if(A<B) result = min(2*A, B-A);
            else result = min(2*B, A-B);
        }
        else result = divConq(K);
        
        cout << "#" << test_case << " " << result << '\n';
        

    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
