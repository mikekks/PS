#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 100005

using namespace std;

typedef long long ll;

int N, K;

struct Node{
    map<string, Node*> Map;
    
    void insert(vector<string>& v, int cnt){
        if(cnt == v.size()) return;
        
        if(Map.find(v[cnt]) == Map.end()){
            Node* node = new Node;
            Map.insert({v[cnt], node});
        }
        
        Map[v[cnt]]->insert(v, cnt+1);
    }
    
    void search(int depth){
        for(auto& i : Map){
            for(int j=0; j<depth; j++){
                cout << "--";
            }
            cout << i.first << '\n';
            i.second->search(depth+1);
            delete i.second;
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    Node* root = new Node;
    
    for(int i=0; i<N; i++){
        cin >> K;
        string tmp;
        vector<string> v;
        for(int j=0; j<K; j++){
            cin >> tmp;
            v.push_back(tmp);
        }
        root->insert(v, 0);
    }
        
    root->search(0);
    
    return 0;
}
