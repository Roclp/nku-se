#ifndef Sort_AT_Adaptor_H
#define Sort_AT_Adaptor_H

#include "access_table.h"
#include "line_ptrs.h"
#include <ostream>

class Sort_AT_Adaptor: private Access_Table<Line_Ptrs>  {
public:
    virtual int make_table (char *buffer, size_t num_lines); 
	Line_Ptrs &operator[ ] (size_t index){
		return element(index);
	}
    size_t size () const{
		return length();
	}	
	friend std::ostream & operator<< ( std::ostream & os, Sort_AT_Adaptor & sort_at_adaptor );
};

#endif