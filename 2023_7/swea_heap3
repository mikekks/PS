#include <iostream>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;
int N;
int result[200001];

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
        int A;
        memset(result, '\0', sizeof(result));
        cin >> N >> A;
        
        priority_queue<int> max_q;
        priority_queue<int, vector<int>, greater<int>> min_q;

        max_q.push(A);
        min_q.push(A);

        int cur = A;
        
        for(int i=0; i<N; i++){
            int x, y;
            cin >> x >> y;
                        
            if(cur > x && cur > y){
                max_q.push(x);
                max_q.push(y);
                max_q.pop();
    
                result[i+1] = max_q.top();
                cur = max_q.top();
                min_q.push(cur);

            }
            else if(cur > x && cur < y){
                result[i+1] = max_q.top();
                max_q.push(x);
                min_q.push(y);
            }
            else if(cur < x && cur > y){
                result[i+1] = max_q.top();
                min_q.push(x);
                max_q.push(y);
            }
            else{
                min_q.push(x);
                min_q.push(y);
                min_q.pop();
    
                result[i+1] = min_q.top();
                cur = min_q.top();
                max_q.push(cur);
                
            }
            
        }
        
        long long total = 0;
        for(int i=1; i<=N; i++)
            total = (total + result[i]) % 20171109;
        
        cout << "#" << test_case << " " << total << '\n';

    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
