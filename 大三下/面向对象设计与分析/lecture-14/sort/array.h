#ifndef Array_H
#define Array_H

#include "stdafx.h"

template <class T>
class Array {
  public:	
	void Resize (size_t size = 0){
		_size = size;
		_array = new T [_size];
	}

	~Array( ){
		delete [] _array;
	};

	T &operator[ ](size_t index){
		return _array [index];
	}

	size_t size () const{
		return _size;
	}

  private:
	T *    _array;
	size_t _size;
};

#endif