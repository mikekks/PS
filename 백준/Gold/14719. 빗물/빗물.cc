#include<iostream>
#include<algorithm>
#include<vector>
#include<stack>
 
using std::cout; using std::cin;
using std::vector;
 
int H, W;
int answer;
 
int main() {
	std::ios::sync_with_stdio(false);
	cin.tie(nullptr); cout.tie(nullptr);
 
	std::stack<int> st;
	cin >> H >> W;
 
	int max = 0;
 
	while (W--) {
		int h;
		cin >> h;
		
		int cnt = 0;
		while (!st.empty() && st.top() < h) {
 
			// 여기에 들어온 스택 원소들은 pop하고 다시 채워 놓을거 없이 버려도 된다. 
			if (h > max && st.top()<=max) {  
				answer += max - st.top();
				st.pop();
			
			}
            // 빗물을 계산하고 높이 h 만큼 다시 스택에 쌓는다. ( Key Point )
			else {
				answer += h - st.top();
				st.pop();
				cnt++;
			}
		}
		if (cnt) {
			for (int i = 0; i < cnt; i++) {
				st.push(h);
			}
		}
 
		max = std::max(max, h);  // 왼쪽 최고 값.
		st.push(h);
 
	}
	cout << answer;
 
	return 0;
}