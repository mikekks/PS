#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>

#define MAX 30

using namespace std;

long long ans = 0;
int N;
bool check[MAX];
vector<char> alphaList;
pair<long long, long long> input[MAX];

long long alloc[MAX];
bool visited[15];

int main(void)
{
    cin >> N;
    
    int cnt = 0;

    for(int i=0; i<N; i++){
        string tmp;
        cin >> tmp;
        long long power = tmp.size() - 1;
        
        for(int j=0; j<tmp.size(); j++){
            input[tmp[j] - 'A'].first += pow(10, power--);
            
            if(check[tmp[j] - 'A'] == false){
                check[tmp[j] - 'A'] = true;
                cnt++;
            }
        }
    }
    
    //dfs(0, 0);
    sort(input, input+MAX);
    int mul = 9;
    
    for(int i=MAX-1; i>=0; i--){
        if(cnt == 0){
            break;
        }
        cnt--;
        ans += input[i].first * mul--;
    }
    
    cout << ans << '\n';
    

    return 0;
}
