import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

public class MorphologyTest {

	@Test
	public void test() throws JsonProcessingException {
		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
        String strToAnalyze = "국제학술지 JAMA \"천천히 치료하는게 이득일 수도\" 당뇨성 황반부종 환자 702명 조사결과 성인 실명의 주요 위험요인으로 꼽히는 ‘당뇨병성 황반부종’의 경우, 의료계에서는 초기 공격적인 치료를 강조하고 있지만 실제 혜택은 적다는 평가가 나와 새롭게 주목을 받고 있다. 노스웨스턴 대학(NU)이 16일(현지시간) 웹사이트에 게재한 이 논문은 당뇨성 황반부종(DME) 에 대한 임상연구자 네트워크 (DRCR)가 진행한 702명에 대한 대규모 무작위 임상 조사결과로 국제학술지인 JAMA 최근호에도 게재된 것이다. 무엇보다 미국국립보건원(NIH)의 지원을 받은 이번 조사결과는, 정상에 가깝게 시력이 유지되는 Cl-DME 환자를 대상으로 치료 관리 전략을 평가한 첫 사례로 주목받았다. 정기적인 관찰만 진행하는 전략을 시행하거나 레이저 응고술 치료, 애플리버셉트 치료군으로 나누어 2년 동안의 결과지를 비교한 결과 환자 모니터링 시행군과 초기 치료군 사이에 성과는 비슷했다. 조사 대상의 거의 4분의 3은 아예 주사치료가 필요 없다는 결론이 나왔다. 주요기사 “일감 없어 주52시간도 일 못해”…일찍 문닫는 공장들 5·18기념사서 울컥…文대통령은 왜 ‘미안하다’ 했나 이 때문에 앞으로 이를 임상현장에 적용할 경우, 안구내 약물 주사에 따른 치료 부담과 경제적인 부담을 줄일 수 있을 것으로 전망된다. 연구진에 따르면 주사치료의 경우 1회 당 메디케어 소요 평균비용은 1850달러에 이르며 모든 안구주사는 세균 감염이나 염증의 위험이 따르기 때문에, 가능하면 주사를 피하는 편을 택해야 한다. 특히 아직 시력이 정상을 유지하고 있는 상황에서 예비적으로 주사를 택하는 것은 환자를 위해서는 보건의료 정책을 위해서나 경제적 부담과 고통만 가중 시키는 것이라는 결론이 나왔다. DME망막부종은 모세혈관에서 새어나온 출혈이 망막에 고이면서 생기는 증상으로, 세계적으로 시력상실의 가장 큰 원인이 되어 왔고, 치료법에 대해서도 논란이 많았던 병이다. ﻿【서울=뉴시스】 창닫기 기사를 추천 하셨습니다“당뇨합병증 실명…빠른 주사치료 실효없다” 연구발표베스트 추천 뉴스 [오늘과 내일/박용]‘미국’ 없는 세계에 산다면 민경욱 “김정은과도 악수한 영부인, 황교안 지나쳐…분열·협량 상징” 5·18 39주년에 불붙은 유시민 vs 심재철 진실 공방 한국당 “주던 지원도 끊어야 할때인데” 반발 황교안 “정치적 계산 주장 안타까워”…반발 속 5·18 기념식 참석 쌀 부족한데도 떨어지는 北 쌀값의 ‘미스터리’ Copyright by dongA.com All rights reserved";

        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
//            System.out.format("%s %s\n", token.getMorph(), token.getPos());
        }
        
        System.out.println(analyzeResultList.getNouns());

//        System.out.println(analyzeResultList.getMorphesByTags("NP", "NNP", "JKB"));
	}

}
