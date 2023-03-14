#include <iostream>
#include <vector>
#define MOD 1000000007

using namespace::std;
typedef vector<vector<long long>> mat;

mat operator*(mat &a, mat &b){
    mat tmp(2, vector<long long>(2));
    
    for(int i=0; i<2; i++){
        for(int j=0; j<2; j++){
            for(int k=0; k<2; k++){
                tmp[i][j] += a[i][k] * b[k][j];
                
            }
            tmp[i][j] %= MOD;
        }
    }
    
    return tmp;
}


int main(){
    
    long long n;
    scanf("%lld", &n);
    
    mat r = {{1,0}, {0,1}};
    mat a = {{1,1}, {1,0}};
    
    
    while(n>0){
        if(n%2 == 1){
            r = r * a;
        }
        a = a * a;
        n /= 2;
    }
    
    printf("%lld\n", r[1][0]);
}
