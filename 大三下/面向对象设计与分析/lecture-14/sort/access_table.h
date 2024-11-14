#ifndef Access_Table_H
#define Access_Table_H

#include "array.h"

template <class T>
class Access_Table {
  public:
	virtual int make_table (char *buffer, size_t num_lines) =0;	
	T & element (size_t index){
		return _access_array [ index ];
	}; 
	size_t length( ) const{
		return _access_array.size( );
	}
	virtual ~Access_Table ( ){		
		delete _access_buffer;

	}; 

  protected:
     Array<T>  _access_array; 
     char *    _access_buffer; 
};

#endif