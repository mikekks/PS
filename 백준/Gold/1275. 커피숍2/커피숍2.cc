#include <iostream>
#include <vector>
using namespace std;
vector<long long> v;
int N, Q;
long long Tree[400001];
long long init(int start,int end,int node)
{
	if (start == end)
		return Tree[node] = v[start];

	int mid = (start + end) / 2;

	return Tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
}

long long sum(int start,int end,int left,int right,int node)
{
	if (end < left || right < start)
		return 0;
	if (left <= start && end <= right)
		return Tree[node];
	int mid = (start + end) / 2;
	return sum(start, mid, left, right, node * 2) + sum(mid + 1, end, left, right, node * 2 + 1);
}

void update(int start,int end,int node,int idx,long long dif)
{
	if (idx<start || idx>end)
		return;

	Tree[node] += dif;

	if (start == end)
		return;

	int mid = (start + end) / 2;
	update(start, mid, node * 2, idx, dif);
	update(mid + 1, end, node * 2 + 1, idx, dif);

}
int main(void)
{
	cin.tie(0);
	cout.tie(0);
	ios_base::sync_with_stdio(0);
	cin >> N >> Q;
	for (int i = 0; i < N; i++)
	{
		long long num;
		cin >> num;
		v.push_back(num);
	}

	init(0, N - 1, 1);

	for (int i = 0; i < Q; i++)
	{
		int x, y;
		long long a, b;
		cin >> x >> y >> a >> b;

		if (x <= y)
			cout << sum(0, N - 1, x - 1, y - 1, 1) << "\n";
		else
			cout << sum(0, N - 1, y - 1, x - 1, 1) << "\n";

		update(0, N - 1, 1, a - 1, b- v[a - 1]);
		v[a - 1] = b;

	}

}