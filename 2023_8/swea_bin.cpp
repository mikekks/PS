#include <iostream>

using namespace std;

typedef long long ll;

ll N;


int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int test_case;
    int T;
  
    //freopen("input.txt", "r", stdin);
    cin>>T;

    for(test_case = 1; test_case <= T; ++test_case)
    {
        cin >> N;
        ll ans = -1;
        
        ll l = 1;
        ll r = 1e10;
        
        ll m;
        ll mid;
        
        while (l != r) {
            m = (r+l+1) / 2;
            mid = (m*m-m)/2;
            
            if(N == mid){
                ans = m-1;
                break;
            }
            
            if(N < mid){
                r = m-1;
            }
            else{
                l = m;
            }
            
        }
            
        
        cout << "#" << test_case << " " << ans << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

