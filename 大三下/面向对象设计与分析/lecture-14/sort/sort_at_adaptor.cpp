#include "stdafx.h"
#include "options.h"
#include "sort_at_adaptor.h"

int Sort_AT_Adaptor::make_table (char *buffer, size_t num_lines)
{   
	int j;

    _access_buffer = buffer; 
	_access_array.Resize (num_lines);
    for (int i=0; i<num_lines; i++) {
		//default setting
		_access_array[i]._bol = buffer;		
		_access_array[i]._bok = buffer;

		int offset;
		if ( (offset = Options::instance()->column_offset() ) > 0 ) {
			for (j=1; j<offset && *buffer != '\0'; j++)
				++buffer;
            _access_array[i]._bok = buffer;
		} else if ( (offset = Options::instance()->field_offset() ) > 0 ) {
            //skip offset-1 "non-white followed by white" patterns
			for (j=1; j<offset && *buffer!='\0'; j++) {
				while ( ! ( *buffer!=' ' && *buffer!='\t' && 
					        (buffer[1] ==' ' || buffer[1] =='\t')
							)  )
				   ++buffer;
				++buffer;
			}			
			//skipping any trailing blanks
			while ( (*buffer==' ' || *buffer=='\t') && 
				     *buffer!='\0'  ) {
				++buffer;
			}	
            _access_array[i]._bok = buffer;
		}

		//move buffer to point to the next line.
		while ( *buffer ) ++buffer;     // jump to \0
		while ( ! (*buffer) ) ++buffer; // possible two zeros.
	}
	return 0;
}

std::ostream & operator<< ( std::ostream & os, Sort_AT_Adaptor & sort_at_adaptor )
{
	if (Options::instance()->reverse_output() ) {
		for (int i=sort_at_adaptor.size()-1; i>=0; i--) 
			os << sort_at_adaptor._access_array[i]._bol << std::endl;
	}else{	
		for (int i=0; i<sort_at_adaptor.size(); i++) 
			os << sort_at_adaptor._access_array[i]._bol << std::endl;
	}
	return os;
}
