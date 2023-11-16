#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 10001
#define INF 987654321
#define MOD 9901

using namespace std;

int Map[MAX];
vector<int> order;

int T, N;

int main() {
    cin >> T;
    
    while(T > 0){
        T--;
        int t1,t2;
        memset(Map, '\0', sizeof(Map));
        order.clear();
        
        cin >> N;
        
        for(int i=0; i< N-1; i++){
            int a,b;
            cin >> a >> b;
            Map[b] = a;
        }
        
        cin >> t1 >> t2;
        int cur = t1;
        
        while(cur != 0){
            order.push_back(cur);
            cur = Map[cur];
        }
        
        cur = t2;
        bool check = true;
        
        while(check){
            for(int i=0; i<order.size(); i++){
                if(cur == order[i]){
                    cout << cur << '\n';
                    check = false;
                    break;
                }
            }
            
            cur = Map[cur];
        }
        
    }
    
    
    return 0;
}
