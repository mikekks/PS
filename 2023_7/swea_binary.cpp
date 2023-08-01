#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;

int n,p;
bool check[1000001];

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    
    int test_case;
    int T;
    
    //freopen("input.txt", "r", stdin);
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        memset(check, false, sizeof(check));
        cin >> n >> p;
        int ans = 0;
        int last = 0;
        
        
        for(int i=0; i<n; i++){
            int tmp;
            cin >> tmp;
            check[tmp] = true;
            last = max(last,tmp);
        }

        int start = 1;
        int end = 1;
        int cnt = 0;
        
        ans = p+1;
        
        while(end<=last){
            if(check[end]){
                cnt++;
                end++;
                ans = max(ans, cnt);
            }
            else{
                if(p == 0){
                    ans = max(ans, cnt);
                    if(!check[start]) p++;
                    start++;
                    cnt--;
                }
                else{
                    p--;
                    cnt++;
                    end++;
                }
            }
        }
        
        cout << "#" << test_case << " " << ans << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
