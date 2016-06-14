package no.hib.dat100.prosjekt.utsyn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import no.hib.dat100.prosjekt.kontroll.Kontroll;
import no.hib.dat100.prosjekt.modell.Kort;

public class TurButtonListener implements ActionListener {

	private Utsyn utsyn;

	TurButtonListener(Utsyn utsyn) {
		this.utsyn = utsyn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		utsyn.getLogger().info("");

		Kontroll kontroll = utsyn.getKontroll();

		if (kontroll.erSydTur()) {
			if (kontroll.sydForbi()) {
				utsyn.oppdater();
			} else {
				utsyn.spillLyd();
				
			////****************xx*************
			////TODO
				Kort kort = kontroll.foreslaaSyd();
				if (kort != null) {
					utsyn.fremhevKort(kort);
				}

			////TODO
			////****************xx*************

			}
		} else {
			utsyn.spillLyd();
		}
	}
}
