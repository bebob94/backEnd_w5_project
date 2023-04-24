package com.backEnd_w5_project.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.backEnd_w5_project.auth.entity.Dispositivo;
import com.backEnd_w5_project.auth.entity.StatoDispositivo;
import com.backEnd_w5_project.auth.entity.TipoDispositivo;

@Configuration
public class DispositivoConfiguration {

	@Bean("FakeDispositivo")
	@Scope("prototype")
	public Dispositivo fakeDispositivo() {
		
		Dispositivo d = new Dispositivo();
		int numRandom1 = (int) ((Math.random() * 4) + 1);
		switch (numRandom1) {
		
			case 1: {
				d.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
				break;
			}
			
			case 2: {
				d.setStatoDispositivo(StatoDispositivo.DISMESSO);
				break;
			}
			
			case 3: {
				d.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
				break;
			}
			
			case 4: {
				d.setStatoDispositivo(StatoDispositivo.IN_MANUTENZIONE);
				break;
			}

				default:
				d.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
		}

		int numRandom2 = (int) ((Math.random() * 3) + 1);
		switch (numRandom2) {
		
			case 1: {
				d.setTipoDisipositivo(TipoDispositivo.LAPTOP);
				break;
			}
			
			case 2: {
				d.setTipoDisipositivo(TipoDispositivo.SMARTPHONE);
				break;
			}
			
			case 3: {
				d.setTipoDisipositivo(TipoDispositivo.TABLET);
				break;
			}
			
		}
		System.out.println("sto creando");
		return d;
	}
}
