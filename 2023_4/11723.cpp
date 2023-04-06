#include <iostream>
#include <cstring>

using namespace std;

int S[21];
int m , x; 
string input;

int main(void)
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//input & solve
	cin >> m;

	for(int i=0; i<m; i++){
		cin >> input;

		if (input == "add")
		{
			cin >> x;
			S[x] = 1;
		}
		else if (input == "remove")
		{
			cin >> x;
			S[x] = 0;
		}
		else if (input == "check")
		{
			cin >> x;
			if (S[x])cout << "1\n";
			else cout << "0\n";
		}
		else if (input == "toggle")
		{
			cin >> x;
			if (S[x]) S[x] = 0;
			else S[x] = 1;
		}
		else if (input == "all")
		{
			for (int i = 1; i <= 20; i++)
				S[i] = 1;
		}
		else
		{
			for (int i = 1; i <= 20; i++)
				S[i] = 0;
		}
	}

	return 0;
}
