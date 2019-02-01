#include <iostream>
#include <stdio.h>
#include <time.h>
#include <windows.h>

using namespace std;

int main()
{
	int generated;
    int cnt[11];

    srand(time(NULL));

	for(int i=0; i<11; i++) cnt[i] = 0;

	for(int i=0; i<20; i++) {
		generated = rand()%10+1;
		cout << "The number generated is " << generated << endl;
		cnt[generated]++;
	}

	cout << "\nWe ended with the number " << generated << endl << endl;

	for(int i=1; i<11; i++)
		cout << "The number " << i << " was generated " << cnt[i] << (cnt[i]==1 ? " time":" times") << endl;

	return 0;
}
