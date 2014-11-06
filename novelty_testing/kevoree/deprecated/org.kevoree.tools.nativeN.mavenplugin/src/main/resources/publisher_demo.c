/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Created by jed
 * User: jedartois@gmail.com
 * Date: 03/10/12
 * Time: 11:47
 */

#include "events_udp.h"
#include "events_tcp.h"
#include "events_fifo.h"

#include <strings.h>

int main(void)
{

   int i=0;

    Events      t;
    t.ev_type = EV_UPDATE;



  Publisher publisher_udp;
  publisher_udp.port = 8085;
  strcpy (publisher_udp.hostname, "127.0.0.1");


   strcpy(publisher_udp.name_pipe,"/tmp/test");






       for(i=0;i<1000;i++)
       {
                     //send_event_udp(publisher_udp,t);
                             send_event_fifo(publisher_udp,t);
       }

     /*
 Publisher publisher_tcp;
 publisher_tcp.port = 8084;
 strcpy (publisher_tcp.hostname, "127.0.0.1");


       send_event_tcp(publisher_tcp,t);

          send_event_tcp(publisher_tcp,t);

   send_event_tcp(publisher_tcp,t);

          */



}