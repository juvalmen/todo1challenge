import { Kardexoutdetail } from "./kardexoutdetail.interface";

export interface Kardexout{
  idkardexout:number;
	description:string;
	sequential: number;
	idperson: number;
	creationdate: Date;
	kardexOutDetails: Kardexoutdetail[]
}
