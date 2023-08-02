#include<iostream>
#include <set>

using namespace std;

int k;
string s;

int main(int argc, char** argv)
{
     ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int test_case;
    int T;
    
    //freopen("input.txt", "r", stdin);
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {

        cin >> k >> s;
        set<string> sol;
        
        for(int i=0; i<s.length(); i++)
            for(int j=0; j<s.length(); j++)
                sol.insert(s.substr(i, j+1));
        
        cout << "#" << test_case << " ";
        if(sol.size() < k)
            cout << "none" << '\n';
        else{
            set<string>::iterator iter = sol.begin();
            for (int i = 0; i < k - 1; i++)
                iter++;
            cout << *iter << endl;
        }

    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
