#include <iostream>
#include <queue>
#include <list>
#include <climits>
#include <algorithm>
#include <set>

using namespace std;

int T, K;

int main(){
    scanf("%d", &T);
    
    for(int i=0; i<T; i++){
        multiset<int> q;
        
        scanf("%d", &K);

        for(int i=0; i<K; i++){
            char oper;
            int n;
            cin >> oper >> n;

            if(oper == 'I'){
                q.insert(n);
            }
            else{
                if(n == 1){
                    if(!q.empty()){
                        auto iter = q.end();
                        iter--;
                        q.erase(iter);
                    }
                        
                }else{
                    if(!q.empty()){
                        q.erase(q.begin());
                    }
                       
                }
            }
        }
        
        if(q.empty()){
            cout << "EMPTY" << '\n';
        }else{
            auto iter = q.end();
            iter--;
            cout << *iter << " " << *q.begin() << '\n';
            
        }
        
        cin.clear();
    }
}
