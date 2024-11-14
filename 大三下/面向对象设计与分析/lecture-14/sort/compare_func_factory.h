#include <string.h>
#include "Options.h"

class Compare_Func_Factory
{
public:
	typedef int (*Func_Pointer) (const char*, const char*);
    static Compare_Func_Factory * instance( );
	void set_func_pointer( );
	Func_Pointer get_func_pointer();
private:
    Compare_Func_Factory ( );
	static Compare_Func_Factory  _instance;		
	Func_Pointer _func_pointer;
};
