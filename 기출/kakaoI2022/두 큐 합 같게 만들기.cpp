#include <string>
#include <vector>

typedef long long ll;

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    ll answer = -2;
    ll total = 0;
    ll cnt = 0;
    
    ll tmp1 = 0;
    ll tmp2 = 0;
    
    // next idx 의미 25 26 27
    ll idx1 = 0;  
    ll idx2 = 0;
    ll last = queue1.size();
    
    for(int i=0; i<queue1.size(); i++){
        tmp1 += queue1[i];
        tmp2 += queue2[i];
    }
    
        
    while(tmp1 != tmp2){
        if(idx1 == 2*last+1 || idx2 == 2*last+1) return -1;
        
        
        if(tmp1 > tmp2){
            tmp1 -= queue1[idx1];
            tmp2 += queue1[idx1];
            queue2.push_back(queue1[idx1]);
            idx1++;
        }
        else{
            tmp2 -= queue2[idx2];
            tmp1 += queue2[idx2];
            queue1.push_back(queue2[idx2]);
            idx2++;
        }
        cnt++;
    }
    
    answer = cnt;
    
    return answer;
}
