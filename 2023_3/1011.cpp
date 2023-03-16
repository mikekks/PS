#include <iostream>
#include <queue>
#include <cmath>

using namespace::std;

int T;
string r;


int main(){
    scanf("%d", &T);
    
    for(int i=0; i<T; i++){
        long long tmp1, tmp2;
        long long result = 0;
        long long m = 0;
        
        cin >> tmp1 >> tmp2;
        while(result * result <= tmp2-tmp1)
            result++;
        result--;
        
        m = 2*result - 1;
        
        long long t = tmp2-tmp1-result*result;
        t = (long long)ceil((double)t / (double)result);
        m += t;
        
        printf("%lld\n", m);
        
    }
    
}
