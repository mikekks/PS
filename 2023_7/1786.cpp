#include <iostream>
#include <vector>

using namespace std;

string T, P;

vector<int> cal_pi(){
    int m = (int)P.size();
    int j = 0;
    vector<int> pi(m, 0);
    
    for(int i=1; i<m; i++){
        while(j>0 && P[i] != P[j])
            j = pi[j-1];
        if(P[i] == P[j])
            pi[i] = ++j;
    }
    
    return pi;
}



vector<int> kmp(){
    vector<int> sol;
    auto pi = cal_pi();
    int n = (int)T.size();
    int m = (int)P.size();
    int j = 0;
    
    for(int i=0; i<n; i++){
        while(j>0 && T[i] != P[j])
            j = pi[j-1];
        if(T[i] == P[j]){
            if(j == m-1){
                sol.push_back(i-m+1);
                j = pi[j];
            }else{
                j++;
            }
        }
    }
    
    return sol;
}


int main(){
    
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    getline(cin, T);
    getline(cin, P);
    
    auto result = kmp();
    
    cout << result.size() << '\n';
    for(auto i : result)
        cout << i+1 << " ";
    cout << '\n';
    
    return 0;
}
