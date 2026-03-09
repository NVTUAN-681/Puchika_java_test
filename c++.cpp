//#include <iostream>
//#include <cmath>
//#include <vector>
//#include <list>
//#include <stack>
//#include <iomanip>
//#include <climits>
//#include <string>

#include <bits/stdc++.h>
#include <conio.h>

using namespace std;

//#define endl "\n"

int rows, columns, arr[50][50];
map<int, vector<pair<int, int>>> m;
vector<int> v;

bool fullNeighbors(int x, int y)
{
	if(arr[x + 1][y + 1] && arr[x][y + 1] && arr[x + 1][y] && arr[x - 1][y - 1])
		return true;
	return false;
}

bool checkLineX(int x1, int y1, int x2, int y2) //cung hang thang
{
	if(x1 != x2)
		return false;
	else
	{
		int left = y1 < y2 ? y1 : y2;
		int right = y1 > y2 ? y1 : y2;		
		for(int i = left + 1; i < right; i++)
			if(arr[x1][i] != 0)
				return false;	
	}
	return true;
}
bool checkLineY(int x1, int y1, int x2, int y2) //cung cot thang
{
	if(y1 != y2)
		return false;
	else
	{
		int above = x1 < x2 ? x1 : x2;
		int below = x1 > x2 ? x1 : x2;
		for(int i = above + 1; i < below; i++)
			if(arr[i][y1] != 0)
				return false;
	}
	return true;
}

bool checkZLX(int x1, int y1, int x2, int y2) //hinh chu z, l nam
{
	if(x1 == x2)
		return false;
	for(int i = y1 + 1; i <= y2; i++)
	{
		if(arr[x1][i] != 0)
			return false;
		else
			if(checkLineY(x1, i, x2, y2))
				return true;
			else if(checkLineY(x1, i, x2 + 1, i) && checkLineX(x2, i, x2, y2))
				return true;
	}
	return false;
}

bool checkZLY(int x1, int y1, int x2, int y2) //hinh chu z dung, l
{
	if(y1 == y2)
		return false;
	for(int i = x1 + 1; i <= x2; i++)
	{
		if(arr[i][y1] != 0)
			return false;
		else
			if(checkLineX(i, y1, x2, y2))
				return true;
			else if(checkLineX(i, y1, i, y2 + 1) && checkLineY(i, y2, x2, y2))
				return true;
	}
	return false;
}

bool checkUX(int x1, int y1, int x2, int y2) //hinh chu u nam
{
	if(x1 == x2)
		return false;
	int right = y1 < y2 ? y1 : y2;
	int left = y1 > y2 ? y1 : y2;
	for(int i = left + 1; i <= columns; i++)
	{
		if(checkLineX(x1, y1, x1, i + 1))
			if(checkLineX(x2, y2, x2, i + 1))
			{
				if(checkLineY(x1, i, x2, i))
					return true;
			}
			else
				break;
		else
			break;				
	}
	for(int i = right - 1; i >= 0; i--)
	{
		if(checkLineX(x1, y1, x1, i - 1))
			if(checkLineX(x2, y2, x2, i - 1))
			{
				if(checkLineY(x1, i, x2, i))
					return true;
			}
			else
				break;
		else
			break;				
	}
	return false;
}

bool checkUY(int x1, int y1, int x2, int y2) //hinh chu u
{
	if(y1 == y2)
		return false;
	int above = x1 < x2 ? x1 : x2;
	int below = x1 > x2 ? x1 : x2;
	for(int i = above - 1; i >= 0; i--)
	{
		if(checkLineY(x1, y1, i - 1, y1))
			if(checkLineY(x2, y2, i - 1, y2))
			{
				if(checkLineX(i, y1, i, y2))
					return true;
			}
			else
				break;
		else
			break;
	}
	for(int i = below + 1; i <= rows; i++)
	{	
		if(checkLineY(x1, y1, i + 1, y1))
			if(checkLineY(x2, y2, i + 1, y2))
			{
				if(checkLineX(i, y1, i, y2))
					return true;
			}
			else
				break;
		else
			break;
	}
	return false;
}

bool pathFinding(int x1, int y1, int x2, int y2)
{
	if(x1 == x2 && y1 == y2)
		return false;
	else if(checkLineX(x1, y1, x2, y2))
	{
		//cout << "checkLineX" << endl;
		return true;		
	}
	else if(checkLineY(x1, y1, x2, y2))
	{
		//cout << "checkLineY" << endl;
		return true;		
	}
	else if(checkZLX(x1, y1, x2, y2))
	{
		//cout << "checkZLX" << endl;
		return true;		
	}
	else if(checkZLY(x1, y1, x2, y2))
	{
		//cout << "checkZLY" << endl;
		return true;		
	}
	else if(checkUX(x1, y1, x2, y2))
	{
		//cout << "checkUX" << endl;
		return true;		
	}
	else if(checkUY(x1, y1, x2, y2))
	{
		//cout << "checkUY" << endl;
		return true;		
	}
	//cout << "false" << endl;
	return false;
}

bool pathFinding()
{
	for(pair<int, vector<pair<int, int>>> p : m)
	{
		for(int i = 0; i < p.second.size(); i++)
			for(int j = i + 1; j < p.second.size(); j++)
				if(pathFinding(p.second[i].first, p.second[i].second, p.second[j].first, p.second[j].second))
					return true;
	}
	return false;
}

void shuffle()
{
	do{
		// Xáo trộn ngẫu nhiên mảng v
		// 1. Khởi tạo seed dựa trên thời gian thực để kết quả ngẫu nhiên mỗi lần chạy
		unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
		    
		// 2. Sử dụng bộ tạo số ngẫu nhiên Mersenne Twister (mt19937)
		std::shuffle(v.begin(), v.end(), std::default_random_engine(seed));
		    
		// 3. Gán bộ số vào ma trận
		int k = 0;
		m.clear();
		for(int i = 1; i <= rows; i++)
		    for(int j = 1; j <= columns; j++)
		    {
		    	arr[i][j] = v[k];
		    	m[v[k++]].push_back({i, j});
		    }
	}while(!pathFinding());
}

void init()
{
	srand(time(NULL));
	while(((rows = 5 + rand() % 6) * (columns = 5 + rand() % 6)) & 1);
	cout << rows << " " << columns << endl;
	for(int i = 0; i < rows * columns; i += 2)
	{
		int random_number = 1 + rand() % 20;
		v.push_back(random_number);
		v.push_back(random_number);
	}
	shuffle();
}

void static_matrix()
{
	rows = 5, columns = 5;
	//arr = {{0, 1, 2, 3, 4, 5}, {1, 1, 1, 1, 1, 1}, {2, 1, 0, 0, 0, 1}, {3, 1, 1, 2, 1, 1}, {4, 1, 1, 1, 1, 1}, {5, 1, 1, 1, 1, 1}};
}

bool empty()
{
	for(int i = 0; i < rows; i++)
		for(int j = 0; j < columns; j++)
			if(arr[i][j] != 0)
				return false;
	return true;
}

void print()
{
	for(int i = 0; i <= rows; i++)
	{
		for(int j = 0; j <= columns; j++)
		{
			if(i == 0)
				cout << left << setw(4) << j;
			else if(j == 0)
				cout << left << setw(4) << i;
			else if(arr[i][j])
				cout << left << setw(4) << arr[i][j];
			else
				cout << left << setw(4) << " ";

		}
		if(i == 0)
			cout << left << setw(8) << "Hint" << "Sap xep lai";					
		cout << endl;
	}	
}

void hint()
{
	for(pair<int, vector<pair<int, int>>> p : m)
	{
		for(int i = 0; i < p.second.size(); i++)
			for(int j = i + 1; j < p.second.size(); j++)
				if(pathFinding(p.second[i].first, p.second[i].second, p.second[j].first, p.second[j].second))
				{
					cout << "(" << p.second[i].first << " , " << p.second[i].second << ") - (" << p.second[j].first << " , " << p.second[j].second << ")" << endl;
					return;
				}
	}	
}

void move(int &a, int &b)
{
	a = b = 1; //a la hoanh do, b la tung do
	cout << a << " " << b;
	int key;
	while(true)
	{
		key = getch();
		if(key == 224)
		{
			key = getch();
	    	switch(key)
			{
	            case 72:
	                //std::cout << "Mui ten LEN" << std::endl;
	                if(a > 1)
	                	--a;
	                else
	                	a = rows;
		            break;
	            case 80:
	                //std::cout << "Mui ten XUONG" << std::endl;
	                if(a < rows)
	                	++a;
	                else
	                	a = 1;
	                break;
	            case 75:
	                //std::cout << "Mui ten TRAI" << std::endl;
	                if(b > 1)
	                	--b;
	                else
	                	b = columns + 2;
	                break;
	            case 77:
	                //std::cout << "Mui ten PHAI" << std::endl;
	                if(b <= columns + 1)
	                	++b;
					else
						b = 1;
	                break;            
	        }
	        if(b == columns + 1)
	            cout << "\r       \r" << "Hint";
	        else if(b == columns + 2)
	            cout << "\r       \r" << "Sap xep";
			else
				cout << "\r       \r" << a << " " << b;
    	}
    	else
    	{
    		cout << endl;
    		break;
		}		
	}
}

void play()
{
	int x1, y1 = 0, x2, y2;
	while(!empty())
	{
		print();
		if(y1 == columns + 1)
			hint();		
		cout << "Nhap toa do diem dau hoac support:\n";
		move(x1, y1);		
		if(y1 == columns + 1)
			continue;
		else if(y1 == columns + 2)
		{
			shuffle();
			continue;
		}			
		cout << "Nhap toa do diem sau:\n";
		move(x2, y2);
		if(arr[x1][y1] == arr[x2][y2] && pathFinding(x1, y1, x2, y2))
		{
			int temp = arr[x1][y1];
			arr[x1][y1] = arr[x2][y2] = 0;
			
			//xoa cac phan tu da chon trong vector va thay no bang phan tu 0
			for(vector<int> :: iterator it = v.begin(); it < v.end(); it++)
				if(*it == temp)
					v.erase(it);
			v.push_back(0);
			for(vector<int> :: iterator it = v.begin(); it < v.end(); it++)
				if(*it == temp)
					v.erase(it);
			v.push_back(0);
			
			//xoa cac cap da chon trong map
			for(vector<pair<int, int>> :: iterator it = m[temp].begin(); it < m[temp].end(); it++)
				if((*it).first == x1 && (*it).second == y1)
					m[temp].erase(it);
			for(vector<pair<int, int>> :: iterator it = m[temp].begin(); it < m[temp].end(); it++)
				if((*it).first == x2 && (*it).second == y2)
					m[temp].erase(it);
			
			if(pathFinding() == false)
				shuffle();
		}
		//system("cls");
	}
}

int main()
{
//    ios_base::sync_with_stdio(false);
//    cin.tie(0);
//    cout.tie(0);
    
    init();
    //static_matrix();
    play();
    return 0;
}