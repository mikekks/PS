#include <string>
#include <vector>

using namespace std;

int S;
int N;
int ans;
vector<int> answer;
vector<int> values;


vector<int> solution(int n, int s) {
    
    S = s;
    N = n;
    
    
    int div = s / n;
    int mod = s % n;
    
    if(n > s){
        answer.push_back(-1);
        return answer;
    }
    
    for(int i=0; i<n; i++){
        answer.push_back(div);
    }
    
    for(int i=answer.size()-1; i>0 && mod > 0; i--){
        answer[i]++;
        mod--;
    }
    
    return answer;
}
