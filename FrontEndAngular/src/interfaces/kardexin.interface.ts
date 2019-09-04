import { Kardexindetail } from "./kardexindetail.interface";

export interface Kardexin{
  idkardexin:number;
	description:string;
	sequential: number;
	idperson: number;
	creationdate: Date;
	kardexInDetails: Kardexindetail[]
}
