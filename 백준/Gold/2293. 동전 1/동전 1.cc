#include <iostream>
#include <vector>
using namespace std;

long long countWaysToMakeChange(const vector<int>& coins, int k) {
    int n = coins.size();
    vector<long long> dp(k + 1, 0);
    dp[0] = 1;  // 0원을 만드는 경우는 한 가지(아무 동전도 사용하지 않는 경우)

    // 각 동전에 대해 경우의 수를 누적 계산
    for (int i = 0; i < n; ++i) {
        for (int j = coins[i]; j <= k; ++j) {
            dp[j] += dp[j - coins[i]];
        }
    }

    return dp[k];
}

int main() {
    int n, k;
    cin >> n;
    cin >> k;

    vector<int> coins(n);
    for (int i = 0; i < n; ++i) {
        cin >> coins[i];
    }

    long long result = countWaysToMakeChange(coins, k);
    cout << result << endl;

    return 0;
}
